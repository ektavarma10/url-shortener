package com.project.URLShortener.controller;

import com.project.URLShortener.dto.InsightsResponse;
import com.project.URLShortener.dto.URLRequest;
import com.project.URLShortener.service.URLShortenerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class URLShortenerApi {
    private final URLShortenerService urlShortenerService;

    @GetMapping("/health")
    public ResponseEntity<Long> health() {
        return ResponseEntity.ok(Instant.now().getEpochSecond());
    }

    @PostMapping("/shorten")
    public ResponseEntity<String> getShortenedURL(@RequestBody URLRequest request) {
        return ResponseEntity.ok(urlShortenerService.shortenURL(request));
    }

    @GetMapping("/fetch/{shortId}")
    public ResponseEntity<String> getLongURL(@PathVariable(value = "shortId") String shortId) {
        return ResponseEntity.ok(urlShortenerService.getLongURL(shortId));
    }

    @GetMapping("/insights/{shortId}")
    public ResponseEntity<InsightsResponse> getInsights(@PathVariable(value = "shortId") String shortId) {
        return ResponseEntity.ok(urlShortenerService.getInsights(shortId));
    }


}
