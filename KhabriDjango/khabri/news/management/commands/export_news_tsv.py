import csv
import os
from django.core.management.base import BaseCommand
from news.models import News

class Command(BaseCommand):
    help = 'Export news data to a TSV file'

    def handle(self, *args, **kwargs):
        # Define the path to the TSV file
        tsv_file_path = os.path.join(os.getcwd(), 'news_data.tsv')
        
        # Query all news entries
        news_items = News.objects.all()
        
        # Define the fields to export, including country and category
        fields = ['unique_id', 'title', 'description', 'content', 'url', 'image', 'published_at', 'source_name', 'source_url', 'country', 'category']
        
        # Write the data to a TSV file
        with open(tsv_file_path, mode='w', newline='', encoding='utf-8') as file:
            writer = csv.DictWriter(file, fieldnames=fields, delimiter='\t')
            writer.writeheader()  # Write the header
            
            for news in news_items:
                writer.writerow({
                    'unique_id': news.unique_id,
                    'title': news.title,
                    'description': news.description,
                    'content': news.content,
                    'url': news.url,
                    'image': news.image,
                    'published_at': news.published_at,
                    'source_name': news.source_name,
                    'source_url': news.source_url,
                    'country': news.country,
                    'category': news.category,
                })
        
        self.stdout.write(self.style.SUCCESS(f'Successfully exported news data to {tsv_file_path}'))
