package com.example.youtubedemo.serviceTest;

import com.example.youtubedemo.Entity.Channel;
import com.example.youtubedemo.repositories.ChannelRepository;
import com.example.youtubedemo.services.ChannelService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ChannelServiceTest {

    @Mock
    private ChannelRepository channelRepository;

    @InjectMocks
    private ChannelService channelService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAllChannels() {
        // Given
        List<Channel> channels = new ArrayList<>();
        channels.add(new Channel());
        channels.add(new Channel());
        when(channelRepository.findAll()).thenReturn(channels);

        // When
        List<Channel> result = channelService.getAllChannels();

        // Then
        assertEquals(channels.size(), result.size());
        verify(channelRepository, times(1)).findAll();
    }

    @Test
    void testGetChannelById() {
        // Given
        Long id = 1L;
        Channel channel = new Channel();
        channel.setId(id);
        when(channelRepository.findById(id)).thenReturn(Optional.of(channel));

        // When
        Channel result = channelService.getChannelById(id);

        // Then
        assertNotNull(result);
        assertEquals(id, result.getId());
        verify(channelRepository, times(1)).findById(id);
    }

    @Test
    void testGetChannelByIdNotFound() {
        // Given
        Long id = 1L;
        when(channelRepository.findById(id)).thenReturn(Optional.empty());

        // When / Then
        assertThrows(NoSuchElementException.class, () -> channelService.getChannelById(id));
        verify(channelRepository, times(1)).findById(id);
    }


}
