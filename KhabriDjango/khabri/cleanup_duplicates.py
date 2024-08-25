# cleanup_duplicates.py

import os
import django

os.environ.setdefault('DJANGO_SETTINGS_MODULE', 'news_app.settings')
django.setup()

from news.models import News
from django.db.models import Count

# Find duplicate titles
duplicates = News.objects.values('title').annotate(title_count=Count('title')).filter(title_count__gt=1)

for duplicate in duplicates:
    title = duplicate['title']
    news_items = News.objects.filter(title=title)
    # Keep the first item and delete the rest
    first_item = news_items.first()
    for item in news_items[1:]:
        item.delete()

    print(f'Duplicates removed for title: {title}')
