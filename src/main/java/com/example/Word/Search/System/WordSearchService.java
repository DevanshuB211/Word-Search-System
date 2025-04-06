package com.example.Word.Search.System;

import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WordSearchService {
    private TrieNode root;
    private Map<String, Integer> wordRanks;
    private static final int MAX_WORD_LENGTH = 50;
    private static final int MAX_PREFIX_LENGTH = 20;

    private static class WordRank {
        String word;
        int rank;

        WordRank(String word, int rank) {
            this.word = word;
            this.rank = rank;
        }

        public int getRank() {
            return rank;
        }
    
        public String getWord() {
            return word;
        }
    }

    public WordSearchService() {
        root = new TrieNode();
        wordRanks = new HashMap<>();
    }

    public void insert(String word) {
        if (word == null || word.length() > MAX_WORD_LENGTH) return;
        String lowerWord = word.toLowerCase();
        for (char c : lowerWord.toCharArray()) {
            if (c < 'a' || c > 'z') return; // Only lowercase a-z
        }
        TrieNode node = root;
        for (char c : lowerWord.toCharArray()) {
            int index = c - 'a';
            if (node.children[index] == null) {
                node.children[index] = new TrieNode();
            }
            node = node.children[index];
        }
        if (!node.isEndOfWord) {
            node.isEndOfWord = true;
            wordRanks.put(lowerWord, 0);
        }
    }

    public void loadWordsFromFile() {
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        this.getClass().getResourceAsStream("/data/words.txt")))) {
            String line;
            while ((line = br.readLine()) != null) {
                String word = line.trim();
                if (!word.isEmpty()) {
                    insert(word);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load words: " + e.getMessage());
        }
    }

    public List<String> autocomplete(String prefix) {
        if (prefix == null || prefix.length() > MAX_PREFIX_LENGTH) return Collections.emptyList();
        TrieNode node = root;
        String lowerPrefix = prefix.toLowerCase();
        for (char c : lowerPrefix.toCharArray()) {
            int index = c - 'a';
            if (node.children[index] == null) {
                return Collections.emptyList();
            }
            node = node.children[index];
        }

        List<WordRank> rankedWords = new ArrayList<>();
        collectWords(node, new StringBuilder(lowerPrefix), rankedWords);
        
        rankedWords.sort(Comparator
            .comparingInt(WordRank::getRank).reversed()
            .thenComparing(WordRank::getWord));
        
        List<String> results = new ArrayList<>(rankedWords.size());
        for (WordRank wr : rankedWords) {
            results.add(wr.word);
        }
        return results;
    }

    private void collectWords(TrieNode node, StringBuilder prefix, List<WordRank> results) {
        if (node.isEndOfWord) {
            String word = prefix.toString();
            results.add(new WordRank(word, wordRanks.getOrDefault(word, 0)));
        }
        for (char c = 'a'; c <= 'z'; c++) {
            int index = c - 'a';
            if (node.children[index] != null) {
                prefix.append(c);
                collectWords(node.children[index], prefix, results);
                prefix.setLength(prefix.length() - 1);
            }
        }
    }

    public boolean search(String word) {
        if (word == null || word.length() > MAX_WORD_LENGTH) return false;
        TrieNode node = traverseToWord(word);
        if (node != null && node.isEndOfWord) {
            String lowerWord = word.toLowerCase();
            wordRanks.put(lowerWord, wordRanks.getOrDefault(lowerWord, 0) + 1);
            return true;
        }
        return false;
    }

    public int getRank(String word) {
        if (word == null || word.length() > MAX_WORD_LENGTH) return -1;
        String lowerWord = word.toLowerCase();
        TrieNode node = traverseToWord(word);
        if (node != null && node.isEndOfWord) {
            return wordRanks.getOrDefault(lowerWord, 0);
        }
        return -1;
    }

    private TrieNode traverseToWord(String word) {
        TrieNode node = root;
        for (char c : word.toLowerCase().toCharArray()) {
            int index = c - 'a';
            if (node.children[index] == null) {
                return null;
            }
            node = node.children[index];
        }
        return node;
    }
}