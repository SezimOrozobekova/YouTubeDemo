package com.example.youtubedemo.controller;

import com.example.youtubedemo.dto.ChannelDto;
import com.example.youtubedemo.services.ChannelService;
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

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class ChannelControllerTest {

    @Mock
    private ChannelService channelService;

    @InjectMocks
    private ChannelController channelController;

    private MockMvc mockMvc;

    @Test
    public void testGetChannelById() throws Exception {
        // Setup
        ChannelDto channelDto = new ChannelDto(); // create a sample ChannelDto
        when(channelService.getChannelById(anyLong())).thenReturn(channelDto);

        // Test
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/channel/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        // You can add more assertions based on your ChannelDto fields
    }

    @Test
    public void testGetAllChannels() throws Exception {
        // Setup
        ChannelDto channelDto = new ChannelDto(); // create a sample ChannelDto
        when(channelService.getAllChannels()).thenReturn(Collections.singletonList(channelDto));

        // Test
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/channel"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        // You can add more assertions based on your ChannelDto fields
    }

    @org.junit.jupiter.api.BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(channelController).build();
    }
}
