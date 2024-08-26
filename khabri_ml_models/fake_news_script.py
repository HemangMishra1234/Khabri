import numpy as np
import pandas as pd
import pickle
import re
import nltk
from nltk.corpus import stopwords
from nltk.stem import WordNetLemmatizer
nltk.download('stopwords')
nltk.download('wordnet')
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.model_selection import train_test_split
from sklearn.metrics import classification_report, accuracy_score
from sklearn.linear_model import LogisticRegression

with open('logistic_regression_model.pkl', 'rb') as f:
    model = pickle.load(f)
    

tester = pd.read_table("insert-path-here")
new_title = tester["title"]

stop_words = set(stopwords.words('english'))
lemmatizer = WordNetLemmatizer()


def preprocess_text(text):
    # Lowercase the text
    text = text.lower()
    # Remove punctuation and numbers
    text = re.sub(r'[^a-z\s]', '', text)
    # Tokenize and remove stopwords
    tokens = text.split()
    tokens = [word for word in tokens if word not in stop_words]
    # Lemmatize the tokens
    tokens = [lemmatizer.lemmatize(word) for word in tokens]
    # Join tokens back into a string
    return ' '.join(tokens)

vectorizer = TfidfVectorizer(max_features=5000)
preprocessed_titles = new_title.apply(preprocess_text)
X_new = vectorizer.transform(preprocessed_titles)
predictions = model.predict(X_new)

y_test = tester['target']
print(f'Accuracy: {accuracy_score(y_test, predictions) * 100:.2f}%')
print(classification_report(y_test, predictions))