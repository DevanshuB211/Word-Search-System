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
}
