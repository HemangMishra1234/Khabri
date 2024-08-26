from django.shortcuts import render
from django.http import JsonResponse
from django.views.decorators.csrf import csrf_exempt
import requests
import json
from .config import GNEWS_API_KEY
from .models import RecommendedArticle, News, UserData


def home(request):
    return JsonResponse({"message": "Welcome to the News App! Visit /news/ for the latest news."})

def get_news(request):
    url = f'https://gnews.io/api/v4/top-headlines?token={GNEWS_API_KEY}&lang=en'
    response = requests.get(url)
    
    if response.status_code != 200:
        return JsonResponse({"error": "Failed to fetch news."}, status=response.status_code)
    
    data = response.json()

    # Handle cases where 'articles' might not be in the response
    if 'articles' not in data:
        return JsonResponse({"error": "Invalid response structure."}, status=500)

    # Save news articles to the database
    for article in data['articles']:
        News.objects.create(
            title=article['title'],
            description=article['description'],
            content=article.get('content', ''),
            url=article['url'],
            image=article.get('image', ''),
            published_at=article['publishedAt'],
            source_name=article['source']['name'],
            source_url=article['source'].get('url', ''),
        )

    return JsonResponse(data['articles'], safe=False)

@csrf_exempt
def create_user(request):
    if request.method == 'POST':
        try:
            data = json.loads(request.body)
        except json.JSONDecodeError:
            return JsonResponse({"error": "Invalid JSON."}, status=400)

        id = data.get('id')
        name = data.get('name')
        email = data.get('email')
        is_journalist = data.get('is_journalist', False)  # default to False if not provided

        if not id or not name or not email:
            return JsonResponse({"error": "Missing required fields."}, status=400)

        try:
            user_data = UserData.objects.get(id=id)
            user_data.name = name
            user_data.email = email
            user_data.is_journalist = is_journalist
            user_data.save()
            message = "User updated successfully."
        except UserData.DoesNotExist:
            user_data = UserData.objects.create(
                id=id,
                name=name,
                email=email,
                is_journalist=is_journalist
            )
            message = "User created successfully."

        return JsonResponse({
            'id': user_data.id,
            'name': user_data.name,
            'email': user_data.email,
            'is_journalist': user_data.is_journalist,
            'message': message
        }, status=201)

    elif request.method == 'GET':
        user = UserData.objects.all().values()
        context = {"user": list(user)}
        return JsonResponse(context, safe=False)

    return JsonResponse({"error": "Only POST and GET requests are allowed."}, status=400)

def list_users(request):
    users = UserData.objects.all().values('id', 'name', 'email', 'is_journalist')
    return JsonResponse(list(users), safe=False)


def store_recommended_articles(user, article_ids):
    recommended_articles = []
    
    for article_id in article_ids:
        try:
            news_article = News.objects.get(id=article_id)
            recommended_article = RecommendedArticle(user=user, news=news_article)
            recommended_articles.append(recommended_article)
        except News.DoesNotExist:
            # Handle the case where the article ID doesn't exist
            continue
    
    # Bulk create to improve performance
    RecommendedArticle.objects.bulk_create(recommended_articles)