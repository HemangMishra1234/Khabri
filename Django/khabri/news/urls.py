from django.urls import path
from django.contrib.auth import views as auth_views
from . import views

urlpatterns = [
    path('', views.home, name='home'),
    path('login/', auth_views.LoginView.as_view(), name='login'),  # Remove template_name if not using a custom template
    path('logout/', auth_views.LogoutView.as_view(), name='logout'),
    path('news/', views.get_news, name='get_news'),
    path('user/',views.create_user, name='user'),
    path('health/',views.health, name='health'),
    path('science/',views.science, name='science'),
    path('technology/',views.technology, name='technology'),
    path('business/',views.business, name='business'),
    path('entertainment/',views.entertainment, name='entertainment'),
    path('sports/',views.sports, name='sports'),
    
]
