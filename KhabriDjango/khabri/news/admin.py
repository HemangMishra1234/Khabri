from django.contrib import admin
from .models import News

class NewsAdmin(admin.ModelAdmin):
    list_display = ('title', 'published_at', 'source_name')
    search_fields = ('title', 'description', 'content')
    list_per_page = 100  # Set the number of items per page

admin.site.register(News, NewsAdmin)
