package com.example.youtubedemo.services;

import com.example.youtubedemo.Entity.Interaction;
import com.example.youtubedemo.repositories.InteractionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class InteractionService {

    private final InteractionRepository interactionRepository;

    @Autowired
    public InteractionService(InteractionRepository interactionRepository) {
        this.interactionRepository = interactionRepository;
    }

    public List<Interaction> getAllInteractions() {
        return interactionRepository.findAll();
    }

    public Interaction getInteractionById(Long id) {
        return interactionRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Interaction with id " + id + " not found"));
    }

    public Interaction createInteraction(Interaction interaction) {
        return interactionRepository.save(interaction);
    }

    public Interaction updateInteraction(Long id, Interaction updatedInteraction) {
        Interaction existingInteraction = getInteractionById(id);
        existingInteraction.setLiked(updatedInteraction.isLiked());
        return interactionRepository.save(existingInteraction);
    }

    public void deleteInteraction(Long id) {
        Interaction interaction = getInteractionById(id);
        interactionRepository.delete(interaction);
    }
}
