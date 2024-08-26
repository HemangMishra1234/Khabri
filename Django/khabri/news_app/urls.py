# news_app/urls.py
from django.contrib import admin
from django.urls import path, include  # Ensure you are importing 'include'

urlpatterns = [
    path('admin/', admin.site.urls),
    path('', include('news.urls')),  # Handle base URL in news.urls
]
