package com.example.Word.Search.System;

import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service

public class WordSearchService {
    private TrieNode root;
    private Map<String, Integer> wordRanks;

    public WordSearchService() {
        root = new TrieNode();
        wordRanks = new HashMap<>();
    }

    public void insert(String word){
        if (word == null) return;
        String lowerWord = word.toLowerCase();
        TrieNode node = root;
        for (char c : lowerWord.toCharArray()){
            int index = c - 'a';
            if (node.children[index] == null){
                node.children[index] = new TrieNode();
            }
            node = node.children[index];
        }
        if (!node.isEndOfWord){
            node.isEndOfWord = true;
            wordRanks.put(lowerWord, 0);
        }
    }

}
