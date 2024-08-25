from django.contrib import admin
from .models import News

class NewsAdmin(admin.ModelAdmin):
    list_display = ('title', 'published_at', 'source_name', 'is_active')  # Add 'is_active' to the display list
    search_fields = ('title', 'description', 'content')
    list_per_page = 100  # Set the number of items per page
    list_filter = ('is_active',)  # Optionally add filter for 'is_active'

admin.site.register(News, NewsAdmin)
