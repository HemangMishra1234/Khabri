from django.contrib import admin
from .models import News, ArticleInteraction, UserData


class NewsAdmin(admin.ModelAdmin):
    list_display = ('title', 'published_at', 'source_name', 'is_real')
    search_fields = ('title', 'description', 'content')
    list_per_page = 100  # Set the number of items per page


class ArticleInteractionAdmin(admin.ModelAdmin):
    list_display = ('article', 'user_id', 'is_liked', 'is_opened', 'is_reported')
    search_fields = ('article__title', 'user_id')
    list_per_page = 100

admin.site.register(News, NewsAdmin)
admin.site.register(ArticleInteraction, ArticleInteractionAdmin)
admin.site.register(UserData)
