package com.example.youtubedemo.mappers;

import com.example.youtubedemo.Entity.Video;
import com.example.youtubedemo.dto.VideoDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface VideoMapper {
//    @Mapping(source = "channel.id", target = "channel_id")
    @Mapping(source = "id", target = "id")
    @Mapping(source = "title", target = "title")
    VideoDto EntityToDTO(Video video);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "title", target = "title")
    Video DtoToEntity(VideoDto videoDTO);
}
