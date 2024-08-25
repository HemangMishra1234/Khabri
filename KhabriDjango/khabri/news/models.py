from django.db import models
import uuid  # Import uuid to generate unique IDs

class News(models.Model):
    unique_id = models.UUIDField(default=uuid.uuid4, editable=False, unique=True)  # Add unique_id field
    title = models.CharField(max_length=255)
    description = models.TextField(blank=True)
    content = models.TextField(blank=True)
    url = models.URLField(blank=True)
    image = models.URLField(blank=True)
    published_at = models.DateTimeField(blank=True, null=True)
    source_name = models.CharField(max_length=255, blank=True)
    source_url = models.URLField(blank=True)

    def __str__(self):
        return self.title
