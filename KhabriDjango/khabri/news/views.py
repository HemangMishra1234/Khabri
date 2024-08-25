from django.shortcuts import render
import requests
from django.http import JsonResponse
from .config import GNEWS_API_KEY
from django.http import HttpResponse
from .models import News

def home(request):
    return HttpResponse("Welcome to the News App! Visit /news/ for the latest news.")

def get_news(request):
    url = f'https://gnews.io/api/v4/top-headlines?token={GNEWS_API_KEY}&lang=en'
    response = requests.get(url)
    data = response.json()

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
            source_url=article['source']['url'],
        )

    # Optionally return the news data as a JSON response
    return JsonResponse(data['articles'], safe=False)