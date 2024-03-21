package com.example.youtubedemo.mappers;

import com.example.youtubedemo.Entity.Video;
import com.example.youtubedemo.dto.VideoDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper
public interface VideoMapper {

    @Mapping(source = "title", target = "title")
    @Mapping(source = "channel.id", target = "channelId")
    VideoDto entityToDto(Video video);


    @Mapping(source = "title", target = "title")
    @Mapping(source = "channelId", target = "channel.id")
    Video dtoToEntity(VideoDto videoDTO);

}
