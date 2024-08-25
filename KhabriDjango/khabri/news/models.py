from django.db import models
import uuid

class News(models.Model):
    unique_id = models.UUIDField(default=uuid.uuid4, editable=False, unique=True)
    title = models.CharField(max_length=255)
    description = models.TextField(blank=True)
    content = models.TextField(blank=True)
    url = models.URLField(blank=True)
    image = models.URLField(blank=True)
    published_at = models.DateTimeField(blank=True, null=True)
    source_name = models.CharField(max_length=255, blank=True)
    source_url = models.URLField(blank=True)
    country = models.CharField(max_length=255, blank=True)  # Add country field
    category = models.CharField(max_length=255, blank=True)  # Add category field

    def __str__(self):
        return self.title

class ArticleInteraction(models.Model):
    article = models.ForeignKey(News, on_delete=models.CASCADE, related_name='interactions')
    user_id = models.CharField(max_length=255)  # String field for user_id
    is_liked = models.BooleanField(default=False)
    time_spent = models.FloatField(default=0)  # Time spent in seconds
    is_reported = models.BooleanField(default=False)
    no_of_views = models.PositiveIntegerField(default=0)  # Number of views

    def __str__(self):
        return f'Interaction by {self.user_id} on {self.article.title}'
