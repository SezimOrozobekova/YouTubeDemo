package com.example.youtubedemo.serviceTest;

import com.example.youtubedemo.dto.InteractionDto;
import com.example.youtubedemo.Entity.Interaction;
import com.example.youtubedemo.repositories.InteractionRepository;
import com.example.youtubedemo.mappers.InteractionMapper;
import com.example.youtubedemo.services.InteractionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class InteractionServiceTest {

    @Mock
    private InteractionRepository interactionRepository;

    @Mock
    private InteractionMapper interactionMapper;

    @InjectMocks
    private InteractionService interactionService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllInteractions() {
        List<Interaction> interactions = new ArrayList<>();
        interactions.add(new Interaction());
        interactions.add(new Interaction());

        when(interactionRepository.findAll()).thenReturn(interactions);

        List<InteractionDto> interactionDtos = interactionService.getAllInteractions();

        assertEquals(2, interactionDtos.size());
        verify(interactionMapper, times(2)).entityToDto(any());
    }

    @Test
    public void testGetInteractionById() {
        Long id = 1L;
        Interaction interaction = new Interaction();
        InteractionDto interactionDto = new InteractionDto();

        when(interactionRepository.findById(id)).thenReturn(Optional.of(interaction));
        when(interactionMapper.entityToDto(interaction)).thenReturn(interactionDto);

        InteractionDto resultDto = interactionService.getInteractionById(id);

        assertEquals(interactionDto, resultDto);
        verify(interactionMapper, times(1)).entityToDto(interaction);
    }

    @Test
    public void testGetInteractionById_NotFound() {
        Long id = 1L;

        when(interactionRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> interactionService.getInteractionById(id));
    }

    @Test
    public void testCreateInteraction() {
        InteractionDto interactionDto = new InteractionDto();
        Interaction interaction = new Interaction();

        when(interactionMapper.dtoToEntity(interactionDto)).thenReturn(interaction);
        when(interactionRepository.save(interaction)).thenReturn(interaction);
        when(interactionMapper.entityToDto(interaction)).thenReturn(interactionDto);

        InteractionDto savedInteractionDto = interactionService.createInteraction(interactionDto);

        assertEquals(interactionDto, savedInteractionDto);
    }

    // Similarly, you can write tests for updateInteraction and deleteInteraction methods
}
