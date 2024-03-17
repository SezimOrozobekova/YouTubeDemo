package com.example.youtubedemo.controller;

import com.example.youtubedemo.dto.InteractionDto;
import com.example.youtubedemo.services.InteractionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class InteractionControllerTest {

    @Mock
    private InteractionService interactionService;

    @InjectMocks
    private InteractionController interactionController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(interactionController).build();
    }

    @Test
    public void testGetAllInteractions() throws Exception {
        InteractionDto interactionDto = new InteractionDto();
        interactionDto.setId(1L);
        when(interactionService.getAllInteractions()).thenReturn(Collections.singletonList(interactionDto));

        mockMvc.perform(MockMvcRequestBuilders.get("/interaction"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1));
    }

    @Test
    public void testGetInteractionById() throws Exception {
        InteractionDto interactionDto = new InteractionDto();
        interactionDto.setId(1L);
        when(interactionService.getInteractionById(anyLong())).thenReturn(interactionDto);

        mockMvc.perform(MockMvcRequestBuilders.get("/interaction/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    public void testCreateInteraction() throws Exception {
        InteractionDto interactionDto = new InteractionDto();
        interactionDto.setId(1L);
        when(interactionService.createInteraction(any(InteractionDto.class))).thenReturn(interactionDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/interaction")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1}"))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    public void testUpdateInteraction() throws Exception {
        InteractionDto interactionDto = new InteractionDto();
        interactionDto.setId(1L);
        when(interactionService.updateInteraction(anyLong(), any(InteractionDto.class))).thenReturn(interactionDto);

        mockMvc.perform(MockMvcRequestBuilders.put("/interaction/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    public void testDeleteInteraction() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/interaction/{id}", 1L))
                .andExpect(status().isNoContent());
    }
}
