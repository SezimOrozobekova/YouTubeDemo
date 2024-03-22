package com.example.youtubedemo.serviceTest;

import com.example.youtubedemo.dto.ChannelDto;
import com.example.youtubedemo.Entity.Channel;
import com.example.youtubedemo.mappers.ChannelMapper;
import com.example.youtubedemo.repositories.ChannelRepository;
import com.example.youtubedemo.services.ChannelService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ChannelServiceTest {

    @Mock
    private ChannelRepository channelRepository;

    @Mock
    private ChannelMapper channelMapper;

    @InjectMocks
    private ChannelService channelService;

    @Test
    void testGetAllChannels() {
        // Given
        Channel channel1 = new Channel();
        channel1.setName("Channel 1");

        Channel channel2 = new Channel();
        channel2.setName("Channel 2");

        List<Channel> channels = Arrays.asList(channel1, channel2);
        when(channelRepository.findAll()).thenReturn(channels);

        ChannelDto channelDto1 = new ChannelDto();
        channelDto1.setName("Channel 1");

        ChannelDto channelDto2 = new ChannelDto();
        channelDto2.setName("Channel 2");

        when(channelMapper.entityToDto(channel1)).thenReturn(channelDto1);
        when(channelMapper.entityToDto(channel2)).thenReturn(channelDto2);

        // When
        List<ChannelDto> result = channelService.getAllChannels();

        // Then
        assertEquals(2, result.size());
        assertEquals(channelDto1, result.get(0));
        assertEquals(channelDto2, result.get(1));
    }

    @Test
    void testGetChannelById() {
        // Given
        Long channelId = 1L;
        Channel channel = new Channel();
        channel.setId(channelId);
        channel.setName("Test Channel");

        when(channelRepository.findById(channelId)).thenReturn(Optional.of(channel));

        ChannelDto expectedChannelDto = new ChannelDto();
        expectedChannelDto.setName("Test Channel");

        when(channelMapper.entityToDto(channel)).thenReturn(expectedChannelDto);

        // When
        ChannelDto result = channelService.getChannelById(channelId);

        // Then
        assertNotNull(result);
        assertEquals(expectedChannelDto.getName(), result.getName());
    }

    // Write similar tests for other methods such as createChannel, updateChannel, and deleteChannel
}
