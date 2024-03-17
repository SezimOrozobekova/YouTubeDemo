package com.example.youtubedemo.controller;

import com.example.youtubedemo.dto.VideoDto;
import com.example.youtubedemo.services.VideoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class VideoControllerTest {

    @Mock
    private VideoService videoService;

    @InjectMocks
    private VideoController videoController;

    private MockMvc mockMvc;

    @Test
    public void testGetVideoById() throws Exception {
        // Setup
        VideoDto videoDto = new VideoDto(); // create a sample VideoDto
        videoDto.setId(1L);
        when(videoService.getVideoById(anyLong())).thenReturn(videoDto);

        // Test
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/video/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1)); // Assuming your VideoDto has an "id" field
    }

    @Test
    public void testDeleteVideo() throws Exception {
        // Test
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/video/{id}", 1))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testCreateVideo() throws Exception {
        // Setup
        VideoDto videoDto = new VideoDto(); // create a sample VideoDto
        videoDto.setId(1L);
        when(videoService.createVideo(any(VideoDto.class))).thenReturn(videoDto);

        // Test
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/video")
                .content("{}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1)); // Assuming your VideoDto has an "id" field
    }

    @Test
    public void testUpdateVideo() throws Exception {
        // Setup
        VideoDto videoDto = new VideoDto(); // create a sample VideoDto
        videoDto.setId(1L);
        when(videoService.updateVideo(anyLong(), any(VideoDto.class))).thenReturn(videoDto);

        // Test
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/video/{id}", 1)
                .content("{}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1)); // Assuming your VideoDto has an "id" field
    }

    @org.junit.jupiter.api.BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(videoController).build();
    }
}
