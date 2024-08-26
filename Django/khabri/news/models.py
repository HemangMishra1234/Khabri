from django.db import models
from django.db.models.signals import post_save
from django.dispatch import receiver
import os
import pandas as pd
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.metrics.pairwise import cosine_similarity
import uuid

file_path = os.path.join('.', 'news_data.tsv')

df = pd.read_table(file_path)
df['title'] = df['title']+" "+df['description'] + \
    " " + df['content'] + 3*(" "+df['category'])
    
# Preprocess and vectorize titles
vectorizer = TfidfVectorizer(stop_words='english')
tfidf_matrix = vectorizer.fit_transform(df['title'])


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
    
# Function to find the most similar article
def find_most_similar_article(new_title, tfidf_matrix, df,user_id, news_id):
    new_title = News.objects.get(id=news_id).title
    
    # If new_title is a pandas Series, convert it to string
    if isinstance(new_title, pd.Series):
        new_title = new_title.iloc[0]
    new_tfidf = vectorizer.transform([new_title])
    cosine_similarities = cosine_similarity(new_tfidf, tfidf_matrix).flatten()
    # Get index of most similar article
    similar_idx = cosine_similarities.argsort()[-1]
    # print(f"\n\n\n\n{df.iloc[cosine_similarities.argsort()[-10]]}\n\n")
    return df.iloc[similar_idx]['unique_id']



class RecommendedArticle(models.Model):
    user = models.ForeignKey(UserData, on_delete=models.CASCADE)
    news = models.ForeignKey(News, on_delete=models.CASCADE)
    is_interacted = models.CharField(max_length=255, default=1)  # Track if the user interacted with the article

    def __str__(self):
        return f'{self.user.name} - {self.news.title}'


# Signal to trigger ML model and store recommendations
@receiver(post_save, sender=RecommendedArticle)
def provide_recommendations(sender, instance, **kwargs):
    if instance.is_interacted:
        user_id = instance.user.id
        news_id = instance.news.id

        # Assuming you have a function `get_recommended_articles` from your ML model
        recommended_article_ids = get_recommended_articles(user_id, news_id)

        # Store recommendations in the database
        for article_id in recommended_article_ids:
            news_item = News.objects.get(id=article_id)
            RecommendedArticle.objects.create(user=instance.user, news=news_item)

# Function to get recommended articles from ML model (stub for demonstration)
def get_recommended_articles(user_id, news_id):
    news_id = str(news_id)
    # Example usage
    new_article = df.loc[df.unique_id == news_id,"title"]

    similar_article = find_most_similar_article(
        new_article, tfidf_matrix, df,user_id, news_id)
    return similar_article
    # print(
    #     f"Most similar article: {similar_article} (Similarity score: {similarity_score})")