# news/models.py

from django.db import models

class News(models.Model):
    title = models.CharField(max_length=255)
    description = models.TextField()
    content = models.TextField()
    url = models.URLField(max_length=500)
    image = models.URLField(max_length=500, null=True, blank=True)
    published_at = models.DateTimeField()
    source_name = models.CharField(max_length=255)
    source_url = models.URLField(max_length=500)

    def __str__(self):
        return self.title
