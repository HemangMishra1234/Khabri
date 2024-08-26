from django.contrib import admin
from .models import News,UserData

class NewsAdmin(admin.ModelAdmin):
    list_display = ('title', 'published_at', 'source_name', 'is_real')
    search_fields = ('title', 'description', 'content')
    list_per_page = 100  # Set the number of items per page



admin.site.register(News, NewsAdmin)
admin.site.register(UserData)
