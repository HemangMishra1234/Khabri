from django.test import TestCase
from .models import News

class NewsModelTest(TestCase):
    def test_news_titles(self):
        # Create some test data
        News.objects.create(
            title="Test News 1",
            description="Description for Test News 1",
            content="Content for Test News 1",
            url="https://example.com/test-news-1",
            image="https://example.com/test-news-1-image.jpg",
            published_at="2024-08-25T12:00:00Z",
            source_name="Source 1",
            source_url="https://example.com"
        )
        News.objects.create(
            title="Test News 2",
            description="Description for Test News 2",
            content="Content for Test News 2",
            url="https://example.com/test-news-2",
            image="https://example.com/test-news-2-image.jpg",
            published_at="2024-08-25T12:00:00Z",
            source_name="Source 2",
            source_url="https://example.com"
        )

        # Fetch the titles
        titles = News.objects.values_list('title', flat=True)
        
        # Check the results
        self.assertIn("Test News 1", titles)
        self.assertIn("Test News 2", titles)
        print(titles)
