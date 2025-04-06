package com.example.Word.Search.System;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000") // Already added in Step 6 fix
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

    @GetMapping("/search")
    public ResponseEntity<Boolean> search(@RequestParam String word) {
        boolean found = service.search(word);
        return ResponseEntity.ok(found);
    }

    @GetMapping("/rank")
    public ResponseEntity<Integer> getRank(@RequestParam String word) {
        int rank = service.getRank(word);
        return ResponseEntity.ok(rank);
    }
}