import requests
from django.core.management.base import BaseCommand
from news.models import News
import uuid

class Command(BaseCommand):
    help = 'Fetch news from GNews API and store in the database'

    def handle(self, *args, **kwargs):
        api_key = '6957e285ed82965de4bd118bd7c8f3d9'  # Replace with your actual API key
        api_url = 'https://gnews.io/api/v4/top-headlines'
        
        categories = ['general', 'world', 'nation', 'business', 'technology', 'entertainment', 'sports', 'science', 'health']
        
        for category in categories:
            params = {
                'apikey': api_key,
                'lang': 'en',
                'country': 'in',
                'category': category,
                'max': 100
            }
            
            response = requests.get(api_url, params=params)
            if response.status_code == 200:
                data = response.json()
                articles = data.get('articles', [])
                for article in articles:
                    unique_id = str(uuid.uuid4())  # Generate a unique ID
                    # Try to create or update the news item
                    news, created = News.objects.update_or_create(
                        unique_id=unique_id,
                        defaults={
                            'title': article['title'],
                            'description': article.get('description', ''),
                            'content': article.get('content', ''),
                            'url': article.get('url', ''),
                            'image': article.get('image', ''),
                            'published_at': article.get('publishedAt', ''),
                            'source_name': article['source'].get('name', ''),
                            'source_url': article['source'].get('url', '')
                        }
                    )
                    if created:
                        self.stdout.write(self.style.SUCCESS(f'Created news item: {news.title}'))
                    else:
                        self.stdout.write(self.style.SUCCESS(f'Updated news item: {news.title}'))
                self.stdout.write(self.style.SUCCESS(f'Successfully fetched and stored news data for category: {category}'))
            else:
                self.stdout.write(self.style.ERROR(f'Failed to fetch news data for category: {category}'))
