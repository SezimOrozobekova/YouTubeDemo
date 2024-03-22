package com.example.youtubedemo.mappers;

import com.example.youtubedemo.Entity.Comment;
import com.example.youtubedemo.dto.CommentDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CommentMapper {

    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);


    @Mapping(source = "video.id", target = "videoId")
    CommentDto entityToDto(Comment comment);


    @Mapping(source = "videoId", target = "video.id")
    Comment dtoToEntity(CommentDto commentDto);
}
