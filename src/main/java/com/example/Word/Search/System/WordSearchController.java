package com.example.Word.Search.System;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
}