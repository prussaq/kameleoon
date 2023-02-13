package com.example.kameleoon.controller;

import com.example.kameleoon.dto.QuoteDto;
import com.example.kameleoon.service.QuoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("quotes")
public class QuoteController {

    private final QuoteService quoteService;

    @GetMapping
    public ResponseEntity<QuoteDto> getRandomQuote() {
        return ResponseEntity.ok(quoteService.getRandomQuote());
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuoteDto> getQuote(@PathVariable Long id) {
        return ResponseEntity.ok(quoteService.findQuote(id));
    }

    @PostMapping
    public ResponseEntity<String> createQuote(@RequestBody QuoteDto quoteDto) {
        Long quoteId = quoteService.createQuote(quoteDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("quote created with id=" + quoteId);
    }

    @PutMapping
    public ResponseEntity<String> editQuote(@RequestBody QuoteDto quoteDto) {
        System.out.println("put called");
        Long quoteId = quoteService.editQuote(quoteDto);
        System.out.println("service called");
        return ResponseEntity.ok("quote edited with id=" + quoteId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteQuote(@PathVariable Long id) {
        quoteService.deleteQuote(id);
        return ResponseEntity.ok("quote deleted with id=" + id);
    }

    @GetMapping("top-ten")
    public List<QuoteDto> getTopTenQuotes() {
        return quoteService.getTopQuotes(10);
    }

    @GetMapping("worse-ten")
    public List<QuoteDto> getWorseTenQuotes() {
        return quoteService.getWorseQuotes(10);
    }
}
