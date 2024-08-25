import csv
from django.core.management.base import BaseCommand
from news.models import News

class Command(BaseCommand):
    help = 'Export existing news data from the database to a TSV file'

    def handle(self, *args, **kwargs):
        tsv_file_path = 'news_data.tsv'
        with open(tsv_file_path, mode='w', newline='', encoding='utf-8') as file:
            writer = csv.DictWriter(file, fieldnames=[
                'unique_id', 'title', 'description', 'content', 'url', 'image', 'published_at', 'source_name', 'source_url', 'country', 'category', 'is_active'
            ], delimiter='\t')
            writer.writeheader()
            
            # Fetch all news articles from the database
            news_data = News.objects.all().values()
            writer.writerows(news_data)
        
        self.stdout.write(self.style.SUCCESS(f'Successfully exported news data to {tsv_file_path}'))
