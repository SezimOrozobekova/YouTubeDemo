package com.example.youtubedemo.MapperTest;

import com.example.youtubedemo.Entity.Channel;
import com.example.youtubedemo.Entity.Video;
import com.example.youtubedemo.dto.VideoDto;
import com.example.youtubedemo.mappers.VideoMapper;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class VideoMapperTest {

    private final VideoMapper videoMapper = Mappers.getMapper(VideoMapper.class);

    @Test
    public void testEntityToDtoMapping() {
        Channel channel = new Channel();
        channel.setId(123L);
        channel.setName("Test Channel");

        Video video = new Video();
        video.setId(456L);
        video.setTitle("Test Video");
        video.setChannel(channel);


        VideoDto videoDto = videoMapper.entityToDto(video);


        assertEquals(video.getId(), videoDto.getId());
        assertEquals(video.getTitle(), videoDto.getTitle());








    }

    @Test
    public void testDtoToEntityMapping() {
        VideoDto videoDto = new VideoDto();
        videoDto.setTitle("Test Video");
        videoDto.setChannelId(123L);

        Video video = videoMapper.dtoToEntity(videoDto);

        assertEquals(videoDto.getId(), video.getId());
        assertEquals(videoDto.getTitle(), video.getTitle());

    }
}
