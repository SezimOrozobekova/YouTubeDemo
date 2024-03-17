package com.example.youtubedemo.serviceTest;

import com.example.youtubedemo.Entity.Interaction;
import com.example.youtubedemo.repositories.InteractionRepository;
import com.example.youtubedemo.services.InteractionService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
public class InteractionServiceTest {

    @Mock
    private InteractionRepository interactionRepository;

    @InjectMocks
    private InteractionService interactionService;

    @Test
    void testGetAllInteractions() {
        // Given
        List<Interaction> interactions = new ArrayList<>();
        interactions.add(new Interaction());
        interactions.add(new Interaction());
        when(interactionRepository.findAll()).thenReturn(interactions);

        // When
        List<Interaction> result = interactionService.getAllInteractions();

        // Then
        assertEquals(interactions.size(), result.size());
        verify(interactionRepository, times(1)).findAll();
    }

    @Test
    void testGetInteractionById() {
        // Given
        Long id = 1L;
        Interaction interaction = new Interaction();
        interaction.setId(id);
        when(interactionRepository.findById(id)).thenReturn(Optional.of(interaction));

        // When
        Interaction result = interactionService.getInteractionById(id);

        // Then
        assertEquals(id, result.getId());
        verify(interactionRepository, times(1)).findById(id);
    }


}
