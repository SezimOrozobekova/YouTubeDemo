package com.example.youtubedemo.controller;

import com.example.youtubedemo.dto.InteractionDto;
import com.example.youtubedemo.services.InteractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/interaction")
public class InteractionController {

    private final InteractionService interactionService;

    @Autowired
    public InteractionController(InteractionService interactionService) {
        this.interactionService = interactionService;
    }

    @GetMapping
    public ResponseEntity<List<InteractionDto>> getAllInteractions() {
        List<InteractionDto> interactions = interactionService.getAllInteractions();
        return ResponseEntity.ok(interactions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InteractionDto> getInteractionById(@PathVariable Long id) {
        InteractionDto interactionDto = interactionService.getInteractionById(id);
        return ResponseEntity.ok(interactionDto);
    }

    @PostMapping
    public ResponseEntity<InteractionDto> createInteraction(@RequestBody InteractionDto interactionDto) {
        InteractionDto createdInteractionDto = interactionService.createInteraction(interactionDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdInteractionDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InteractionDto> updateInteraction(@PathVariable Long id, @RequestBody InteractionDto updatedInteractionDto) {
        InteractionDto updatedDto = interactionService.updateInteraction(id, updatedInteractionDto);
        return ResponseEntity.ok(updatedDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInteraction(@PathVariable Long id) {
        interactionService.deleteInteraction(id);
        return ResponseEntity.noContent().build();
    }
}
