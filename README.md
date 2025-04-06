# Word Search System

A full-stack application built with **Spring Boot** and **React** (styled with **Bootstrap**) for efficient word management. It supports loading words from a file, autocomplete with ranked suggestions, and searching with rank tracking, presented in a functional and responsive UI.

## Features
- **Load Words**: Import words from `data/words.txt` into a Trie-based data structure.
- **Autocomplete**: Get ranked suggestions based on prefix input (e.g., "ap" → "apple", "apricot").
- **Search & Rank**: Search for words and track their usage rank, incrementing with each search.
- **Responsive UI**: Bootstrap-powered interface for a clean, user-friendly experience.
- **Scalability**: Handles large inputs efficiently.

## Tech Stack
- **Backend**: Spring Boot 3.2.4, Java 17, Maven
- **Frontend**: React 18, Bootstrap 4, Axios
- **Data Structure**: Trie with HashMap for rank storage

## Prerequisites
- **Java 17**: JDK installed .
- **Maven**: Installed .
- **Node.js**: Version 18.17.0 or higher .
- **npm**: Version 9.6.7 or higher .

## Setup
1. Clone the Repository:
   ```bash
   git clone <repository-url>
   cd word-search-system

2. Build: `mvn clean package`
3. Run: `java -jar target/word-search-system-0.0.1-SNAPSHOT.jar`
4. Access: `http://localhost:8080`

## Features
- Load words from `data/words.txt`
- Autocomplete with ranked suggestions
- Search words and display ranks

## Constraints
- Max word length: 50 characters
- Max prefix length: 20 characters
- Only lowercase letters (a-z)
- The rank of any word is an integer (defaulting to 0) and can increase as needed.

## Project Structure
word-search-system/
├── src/
│   ├── main/
│   │   ├── java/com/example/Word/Search/System
│   │   │   ├── TrieNode.java              # Trie node structure
│   │   │   ├── WordSearchService.java     # Core logic for word management
│   │   │   ├── WordSearchController.java  # REST API endpoints
│   │   │   └── WordSearchApplication.java # Spring Boot entry point
│   │   └── resources/
│   │       ├── data/words.txt             # Input word file
│   │       └── application.properties     # Spring Boot config
│   └── test/
│       └── java/com/example/wordsearch/
│           └── WordSearchSystemApplicationTests.java # Unit tests
├── frontend/
│   ├── src/
│   │   ├── App.js                         # Main React component
│   │   ├── App.css                        # Custom CSS
│   │   ├── index.js                       # React entry point
│   │   └── components/
│   │       └── WordSearch.js              # Word search UI component
│   ├── package.json                       # Node dependencies
│   └── public/
│       └── index.html                     # HTML template
├── pom.xml                                   # Maven configuration
├── .gitignore                                # Git ignore rules
└── README.md                                 # This file

## API Endpoints
- GET /api/load: Loads words from words.txt.
- GET /api/autocomplete?prefix=<prefix>: Returns ranked suggestions for a prefix.
- GET /api/search?word=<word>: Searches a word and increments its rank.
- GET /api/rank?word=<word>: Retrieves a word’s current rank.

## Development
**Backend**
- Run: mvn spring-boot:run
- Test: curl http://localhost:8080/api/load

**Frontend**
- Navigate: cd frontend
- Install: npm install
- Run: npm start (runs on http://localhost:3000)

**Full Build**
- Use mvn clean package to build both backend and frontend into a single JAR.