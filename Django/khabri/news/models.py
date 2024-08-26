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
    country = models.CharField(max_length=255, blank=True)
    category = models.CharField(max_length=255, blank=True)
    is_real = models.DecimalField(max_digits=4, decimal_places=3, default=1.0)  # Changed to DecimalField

    def __str__(self):
        return self.title
    
class UserData(models.Model):
    id = models.CharField(primary_key=True, max_length=255)  # Automatically incrementing primary key field
    name = models.CharField(max_length=255)  # Name field
    email = models.CharField(max_length=255)   # Email field with unique constraint
    is_journalist = models.CharField(max_length=255)

    def __str__(self):
        return self.name
    



class RecommendedArticle(models.Model):
    user = models.ForeignKey(UserData, on_delete=models.CASCADE)
    news = models.ForeignKey(News, on_delete=models.CASCADE)
    is_liked = models.BooleanField(default=False)  # Track if the user clicked on it

