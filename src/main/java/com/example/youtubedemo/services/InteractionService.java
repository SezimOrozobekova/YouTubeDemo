package com.example.youtubedemo.services;

import com.example.youtubedemo.dto.InteractionDto;
import com.example.youtubedemo.Entity.Interaction;
import com.example.youtubedemo.repositories.InteractionRepository;
import com.example.youtubedemo.mappers.InteractionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class InteractionService {

    private final InteractionRepository interactionRepository;
    private final InteractionMapper interactionMapper;

    @Autowired
    public InteractionService(InteractionRepository interactionRepository, InteractionMapper interactionMapper) {
        this.interactionRepository = interactionRepository;
        this.interactionMapper = interactionMapper;
    }

    public List<InteractionDto> getAllInteractions() {
        List<Interaction> interactions = interactionRepository.findAll();
        return interactions.stream()
                .map(interactionMapper::entityToDto)
                .collect(Collectors.toList());
    }

    public InteractionDto getInteractionById(Long id) {
        Interaction interaction = interactionRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Interaction with id " + id + " not found"));
        return interactionMapper.entityToDto(interaction);
    }

    public InteractionDto createInteraction(InteractionDto interactionDto) {
        Interaction interaction = interactionMapper.dtoToEntity(interactionDto);
        Interaction savedInteraction = interactionRepository.save(interaction);
        return interactionMapper.entityToDto(savedInteraction);
    }

    public InteractionDto updateInteraction(Long id, InteractionDto updatedInteractionDto) {
        Interaction existingInteraction = interactionRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Interaction with id " + id + " not found"));

        // Update existingInteraction fields
        existingInteraction.setLiked(updatedInteractionDto.isLiked());
        // Assuming you also need to update the video id

        Interaction savedInteraction = interactionRepository.save(existingInteraction);
        return interactionMapper.entityToDto(savedInteraction);
    }

    public void deleteInteraction(Long id) {
        Interaction interaction = interactionRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Interaction with id " + id + " not found"));
        interactionRepository.delete(interaction);
    }
}
