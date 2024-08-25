import requests
import csv
import uuid
import os
from django.core.management.base import BaseCommand
from news.models import News

class Command(BaseCommand):
    help = 'Fetch news from GNews API, store in the database, and export to TSV'

    def handle(self, *args, **kwargs):
        api_key = '1bf3c4ff70752e72a5c0ec9fdcd5941e'  # Replace with your actual API key
        api_url = 'https://gnews.io/api/v4/top-headlines'
        
        categories = ['general', 'world', 'nation', 'business', 'technology', 'entertainment', 'sports', 'science', 'health']
        all_articles = []
        tsv_file_path = 'news_data.tsv'
        
        # Check if the file already exists to determine if we need to write the header
        write_header = not os.path.exists(tsv_file_path)
        
        for category in categories:
            params = {
                'apikey': api_key,
                'lang': 'en',
                'country': 'il',  # Change this to the relevant country code or loop through if needed
                'category': category,
                'max': 100
            }
            
            response = requests.get(api_url, params=params)
            if response.status_code == 200:
                data = response.json()
                articles = data.get('articles', [])
                for article in articles:
                    unique_id = str(uuid.uuid4())  # Generate a unique ID
                    all_articles.append({
                        'unique_id': unique_id,
                        'title': article['title'],
                        'description': article.get('description', ''),
                        'content': article.get('content', ''),
                        'url': article.get('url', ''),
                        'image': article.get('image', ''),
                        'published_at': article.get('publishedAt', ''),
                        'source_name': article['source'].get('name', ''),
                        'source_url': article['source'].get('url', ''),
                        'country': 'il',  # Change this to the relevant country code
                        'category': category,
                        'is_real': '1.000'  # Default value for demonstration
                    })
                    
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
                            'source_url': article['source'].get('url', ''),
                            'country': 'il',  # Change this to the relevant country code
                            'category': category,
                            'is_real': 1.0  # Default value for demonstration
                        }
                    )
                    if created:
                        self.stdout.write(self.style.SUCCESS(f'Created news item: {news.title}'))
                    else:
                        self.stdout.write(self.style.SUCCESS(f'Updated news item: {news.title}'))
                self.stdout.write(self.style.SUCCESS(f'Successfully fetched and stored news data for category: {category}'))
            else:
                self.stdout.write(self.style.ERROR(f'Failed to fetch news data for category: {category}'))
        
        # Append new articles to the existing TSV file
        with open(tsv_file_path, mode='a', newline='', encoding='utf-8') as file:
            writer = csv.DictWriter(file, fieldnames=[
                'unique_id', 'title', 'description', 'content', 'url', 'image', 'published_at', 'source_name', 'source_url', 'country', 'category', 'is_real'
            ], delimiter='\t')
            
            # Write the header only if the file is new
            if write_header:
                writer.writeheader()
                
            writer.writerows(all_articles)
        
        self.stdout.write(self.style.SUCCESS(f'Successfully exported news data to {tsv_file_path}'))
