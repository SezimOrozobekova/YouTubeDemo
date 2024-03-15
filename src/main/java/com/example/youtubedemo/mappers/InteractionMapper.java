package com.example.youtubedemo.mappers;

import com.example.youtubedemo.Entity.Interaction;
import com.example.youtubedemo.dto.InteractionDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface InteractionMapper {
    InteractionMapper INSTANCE = Mappers.getMapper(InteractionMapper.class);
    @Mapping(source = "video.id", target = "videoId")
    InteractionDto entityToDto(Interaction interaction);

    @Mapping(source = "videoId", target = "video.id")
    Interaction dtoToEntity(InteractionDto interactionDto);
}
