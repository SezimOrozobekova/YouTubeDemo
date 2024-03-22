package com.example.youtubedemo.serviceTest;

import com.example.youtubedemo.controller.CommentController;
import com.example.youtubedemo.dto.CommentDto;
import com.example.youtubedemo.services.CommentService;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class CommentControllerTest {

    @Mock
    private CommentService commentService;

    @InjectMocks
    private CommentController commentController;

    private MockMvc mockMvc;

    @Test
    public void testGetAllComments() throws Exception {
        // Setup
        CommentDto commentDto = new CommentDto(); // create a sample CommentDto
        when(commentService.getAllComments()).thenReturn(Collections.singletonList(commentDto));

        // Test
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/comment"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1)); // Assuming your CommentDto has an "id" field
    }

    // Add more test methods for other endpoints as needed

    @org.junit.jupiter.api.BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(commentController).build();
    }
}
