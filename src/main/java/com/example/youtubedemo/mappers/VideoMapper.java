package com.example.youtubedemo.mappers;

import com.example.youtubedemo.Entity.Video;
import com.example.youtubedemo.dto.VideoDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;


@Mapper
public interface VideoMapper {
    @Mapping(source = "id", target = "id")
    @Mapping(source = "title", target = "title")
    @Mapping(source = "channel.id", target = "channelId")
    VideoDto entityToDto(Video video);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "title", target = "title")
    @Mapping(source = "channelId", target = "channel.id")
    Video dtoToEntity(VideoDto videoDTO);

}
