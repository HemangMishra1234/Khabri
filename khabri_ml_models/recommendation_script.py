import pandas as pd
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.metrics.pairwise import cosine_similarity

df = pd.read_table("news_data.tsv")
df['title'] = df['title']+" "+df['description'] + \
    " " + df['content'] + 3*(" "+df['category'])
    
# Preprocess and vectorize titles
vectorizer = TfidfVectorizer(stop_words='english')
tfidf_matrix = vectorizer.fit_transform(df['title'])

# Function to find the most similar article
def find_most_similar_article(new_title, tfidf_matrix, df):
    new_tfidf = vectorizer.transform([new_title])
    cosine_similarities = cosine_similarity(new_tfidf, tfidf_matrix).flatten()
    # Get index of most similar article
    similar_idx = cosine_similarities.argsort()[-1]
    # print(f"\n\n\n\n{df.iloc[cosine_similarities.argsort()[-10]]}\n\n")
    return df.iloc[similar_idx]['title'], cosine_similarities[similar_idx]

# Example usage
new_article = 'military attack'
similar_article, similarity_score = find_most_similar_article(
    new_article, tfidf_matrix, df)
print(
    f"Most similar article: {similar_article} (Similarity score: {similarity_score})")