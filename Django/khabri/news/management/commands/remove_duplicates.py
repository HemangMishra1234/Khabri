from django.core.management.base import BaseCommand
from django.db.models import Count  # Import Count
from news.models import News

class Command(BaseCommand):
    help = 'Remove duplicate news entries based on title and URL'

    def handle(self, *args, **kwargs):
        # Find duplicates based on title and URL
        duplicates = News.objects.values('title', 'url').annotate(count=Count('id')).filter(count__gt=1)
        
        for duplicate in duplicates:
            # Get all duplicates except the first one and delete them
            duplicates_qs = News.objects.filter(title=duplicate['title'], url=duplicate['url'])
            duplicates_qs.exclude(id=duplicates_qs.first().id).delete()
            self.stdout.write(self.style.SUCCESS(f'Removed duplicates for news: {duplicate["title"]}'))

        self.stdout.write(self.style.SUCCESS('Successfully removed duplicate news entries'))
