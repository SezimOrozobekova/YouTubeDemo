package com.example.youtubedemo.exception;

import com.example.youtubedemo.dto.ChannelDto;
import com.example.youtubedemo.mappers.ChannelMapper;
import com.example.youtubedemo.repositories.ChannelRepository;
import com.example.youtubedemo.services.ChannelService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ChannelServiceExceptionHandlerTest {

    @Mock
    private ChannelRepository channelRepository;

    @Mock
    private ChannelMapper channelMapper;

    @InjectMocks
    private ChannelService channelService;

    @Test
    void testGetChannelById_ChannelNotFoundException() {
        // Arrange
        Long channelId = 1L;
        when(channelRepository.findById(channelId)).thenReturn(java.util.Optional.empty());

        // Act and Assert
        NoSuchElementException exception = org.junit.jupiter.api.Assertions.assertThrows(
                NoSuchElementException.class,
                () -> channelService.getChannelById(channelId)
        );
        assertEquals("Channel not found with id: " + channelId, exception.getMessage());
    }
    @Test
    void testUpdateChannel_ChannelNotFoundException() {
        // Arrange
        Long channelId = 1L;
        ChannelDto updatedChannelDto = new ChannelDto(); // Create a dummy updated channel DTO
        when(channelRepository.findById(channelId)).thenReturn(java.util.Optional.empty());

        // Act and Assert
        NoSuchElementException exception = org.junit.jupiter.api.Assertions.assertThrows(
                NoSuchElementException.class,
                () -> channelService.updateChannel(channelId, updatedChannelDto)
        );
        assertEquals("Channel not found with id: " + channelId, exception.getMessage());
    }

    @Test
    void testDeleteChannel_ChannelNotFoundException() {
        // Arrange
        Long channelId = 1L;
        when(channelRepository.existsById(channelId)).thenReturn(false); // Simulate channel not found

        // Act and Assert
        NoSuchElementException exception = org.junit.jupiter.api.Assertions.assertThrows(
                NoSuchElementException.class,
                () -> channelService.deleteChannel(channelId)
        );
        assertEquals("Channel not found with id: " + channelId, exception.getMessage());
    }

}
