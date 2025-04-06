# word-search-system/README.md
# Word Search System

A full-stack Spring Boot and React app for word management.

## Setup
1. Build: `mvn clean package`
2. Run: `java -jar target/word-search-system-0.0.1-SNAPSHOT.jar`
3. Access: `http://localhost:8080`

## Features
- Load words from `data/words.txt`
- Autocomplete with ranked suggestions
- Search words and display ranks

## Constraints
- Max word length: 50 characters
- Max prefix length: 20 characters
- Only lowercase letters (a-z)