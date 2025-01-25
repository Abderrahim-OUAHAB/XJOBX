import requests
from bs4 import BeautifulSoup
import json

# URL du site d'emploi à scraper (exemple pour Indeed)
url = 'https://www.indeed.com/jobs?q=developer&l=Morocco'

def get_html(url):
    headers = {
        'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36'}
    response = requests.get(url, headers=headers)
    return response.text

def extract_jobs(html):
    soup = BeautifulSoup(html, 'html.parser')
    jobs = []

    # Trouver toutes les annonces d'emploi
    job_cards = soup.find_all('div', class_='job_seen_beacon')
    
    for job in job_cards:
        title = job.find('h2', class_='jobTitle').text.strip()
        company = job.find('span', class_='companyName').text.strip() if job.find('span', class_='companyName') else 'Non spécifié'
        location = job.find('div', class_='companyLocation').text.strip() if job.find('div', class_='companyLocation') else 'Non spécifié'
        summary = job.find('div', class_='job-snippet').text.strip() if job.find('div', class_='job-snippet') else 'Aucune description'
        date = job.find('span', class_='date').text.strip() if job.find('span', class_='date') else 'Date non spécifiée'
        
        jobs.append({
            'Title': title,
            'Company': company,
            'Location': location,
            'Description': summary,
            'Date': date,
        })

    return jobs

def save_jobs_to_json(jobs):
    with open('jobs.json', 'w', encoding='utf-8') as f:
        json.dump(jobs, f, ensure_ascii=False, indent=4)

def main():
    html = get_html(url)
    jobs = extract_jobs(html)
    save_jobs_to_json(jobs)

if __name__ == "__main__":
    main()
