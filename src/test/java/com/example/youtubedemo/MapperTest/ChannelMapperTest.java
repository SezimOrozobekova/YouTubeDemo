package com.example.youtubedemo.MapperTest;

import com.example.youtubedemo.Entity.Channel;
import com.example.youtubedemo.dto.ChannelDto;
import com.example.youtubedemo.mappers.ChannelMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ChannelMapperTest {

    @Test
    void testEntityToDto() {
        // Create a Channel entity
        Channel channel = new Channel();
        channel.setName("Channel Name");
        channel.setAvatar("Avatar URL");

        // Use the mapper to convert Channel entity to ChannelDto
        ChannelDto channelDto = ChannelMapper.INSTANCE.entityToDto(channel);

        // Check if the conversion is successful
        assertNotNull(channelDto);
        assertEquals(channel.getName(), channelDto.getName());
        assertEquals(channel.getAvatar(), channelDto.getAvatar());
    }

    @Test
    void testDtoToEntity() {
        // Create a ChannelDto
        ChannelDto channelDto = new ChannelDto();
        channelDto.setName("Channel Name");
        channelDto.setAvatar("Avatar URL");

        // Use the mapper to convert ChannelDto to Channel entity
        Channel channel = ChannelMapper.INSTANCE.dtoToEntity(channelDto);

        // Check if the conversion is successful
        assertNotNull(channel);
        assertEquals(channelDto.getName(), channel.getName());
        assertEquals(channelDto.getAvatar(), channel.getAvatar());
    }
}
