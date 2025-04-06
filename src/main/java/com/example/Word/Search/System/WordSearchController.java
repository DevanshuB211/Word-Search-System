package com.example.Word.Search.System;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class WordSearchController {
    @Autowired
    private WordSearchService service;

    @GetMapping("/load")
    public ResponseEntity<String> loadWords() {
        service.loadWordsFromFile();
        return ResponseEntity.ok("Words loaded from file.");
    }

    @GetMapping("/autocomplete")
    public ResponseEntity<List<String>> autocomplete(@RequestParam String prefix) {
        List<String> suggestions = service.autocomplete(prefix);
        return ResponseEntity.ok(suggestions);
    }
}