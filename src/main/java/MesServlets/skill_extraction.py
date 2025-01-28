# -*- coding: utf-8 -*-
import spacy
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.metrics.pairwise import cosine_similarity
from PyPDF2 import PdfReader
import sys

# Charger le modèle NLP
nlp = spacy.load("en_core_web_sm")

# Fonction pour extraire le texte du CV (PDF)
def extract_text_from_pdf(pdf_path):
    reader = PdfReader(pdf_path)
    text = ""
    for page in reader.pages:
        text += page.extract_text()
    return text

# Fonction pour extraire les compétences
def extract_skills(text):
    doc = nlp(text)
    skills = []
    for token in doc:
        if token.pos_ in ["NOUN", "PROPN"] and not token.is_stop:
            skills.append(token.text)
    return list(set(skills))  # Retourner une liste des compétences uniques

# Fonction pour calculer la similarité (avec seuil)
def calculate_similarity(candidate_skills, job_descriptions):
    tfidf_vectorizer = TfidfVectorizer()
    all_texts = [" ".join(candidate_skills)] + job_descriptions
    tfidf_matrix = tfidf_vectorizer.fit_transform(all_texts)
    similarities = cosine_similarity(tfidf_matrix[0], tfidf_matrix[1:])
    return similarities[0]

# Fonction principale pour extraire les compétences et suggérer les offres
def suggest_jobs(cv_path, job_descriptions, threshold=0.5):
    cv_text = extract_text_from_pdf(cv_path)
    candidate_skills = extract_skills(cv_text)
    
    # Calculer la similarité pour chaque offre et appliquer le seuil
    suggested_ids = []
    similarities = calculate_similarity(candidate_skills, job_descriptions)
    
    for i, similarity in enumerate(similarities):
        if similarity >= threshold:  # Si la similarité dépasse le seuil
            offer_id = job_descriptions[i].split(":")[0]  # Extraire l'ID de l'offre
            suggested_ids.append(offer_id)
    
    return suggested_ids  # Retourner les IDs des offres suggérées

if __name__ == "__main__":
    cv_path = sys.argv[1]
    job_descriptions = sys.argv[2].split("||")  # Sépare les descriptions (au cas où vous voulez utiliser la similarité)
    threshold = 0.2  # Définir un seuil de similarité (par exemple 0.5)

    suggested_ids = suggest_jobs(cv_path, job_descriptions, threshold)

    # Afficher les IDs des offres suggérées
    for offer_id in suggested_ids:
        print(offer_id)  # Affiche chaque ID sur une nouvelle ligne
