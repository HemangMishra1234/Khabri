import csv
import os
from django.core.management.base import BaseCommand
from news.models import News

class Command(BaseCommand):
    help = 'Export news data from the database to TSV'

    def handle(self, *args, **kwargs):
        tsv_file_path = 'news_data.tsv'
        
        # Read existing entries from the TSV file to avoid duplicates
        existing_ids = set()
        if os.path.exists(tsv_file_path):
            with open(tsv_file_path, mode='r', newline='', encoding='utf-8') as file:
                reader = csv.DictReader(file, delimiter='\t')
                existing_ids = {row['unique_id'] for row in reader}
        
        # Fetch all news items from the database
        all_articles = News.objects.all().values(
            'unique_id', 'title', 'description', 'content', 'url', 'image',
            'published_at', 'source_name', 'source_url', 'country', 'category', 'is_real'
        )
        
        # Filter out duplicates
        new_articles = [article for article in all_articles if article['unique_id'] not in existing_ids]
        
        # Append new articles to the existing TSV file
        with open(tsv_file_path, mode='a', newline='', encoding='utf-8') as file:
            writer = csv.DictWriter(file, fieldnames=[
                'unique_id', 'title', 'description', 'content', 'url', 'image',
                'published_at', 'source_name', 'source_url', 'country', 'category', 'is_real'
            ], delimiter='\t')
            
            # Write the header only if the file is new
            if not existing_ids:
                writer.writeheader()
                
            writer.writerows(new_articles)
        
        self.stdout.write(self.style.SUCCESS(f'Successfully exported news data to {tsv_file_path}'))
