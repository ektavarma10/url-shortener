package com.project.URLShortener.controller;

import com.project.URLShortener.service.SequenceGeneratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sequence")
@RequiredArgsConstructor
public class SequenceGeneratorApi {
    private final SequenceGeneratorService sequenceGeneratorService;

    @PostMapping("/initialise")
    public ResponseEntity<String> initialiseSequence() {
        sequenceGeneratorService.initiateSequence();
        return ResponseEntity.ok("sequence initialized");
    }
}
