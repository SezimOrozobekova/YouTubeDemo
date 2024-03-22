package com.example.youtubedemo.controller;

import com.example.youtubedemo.dto.CommentDto;
import com.example.youtubedemo.services.CommentService;
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
public class CommentControllerTest {

    @Mock
    private CommentService commentService;

    @InjectMocks
    private CommentController commentController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(commentController).build();
    }

    @Test
    public void testGetAllComments() throws Exception {
        CommentDto commentDto = new CommentDto();

        when(commentService.getAllComments()).thenReturn(Collections.singletonList(commentDto));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/comment"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1));
    }

    @Test
    public void testGetCommentById() throws Exception {
        CommentDto commentDto = new CommentDto();

        when(commentService.getCommentById(anyLong())).thenReturn(commentDto);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/comment/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    public void testCreateComment() throws Exception {
        CommentDto commentDto = new CommentDto();

        when(commentService.createComment(any(CommentDto.class))).thenReturn(commentDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/comment")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":1}"))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    public void testUpdateComment() throws Exception {
        CommentDto commentDto = new CommentDto();

        when(commentService.updateComment(anyLong(), any(CommentDto.class))).thenReturn(commentDto);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/comment/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":1}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    public void testDeleteComment() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/comment/{id}", 1L))
                .andExpect(status().isNoContent());
    }
}
