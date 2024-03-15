package com.example.youtubedemo.mappers;

import com.example.youtubedemo.Entity.Channel;
import com.example.youtubedemo.dto.ChannelDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ChannelMapper {

    ChannelMapper INSTANCE = Mappers.getMapper(ChannelMapper.class);

    @Mapping(source = "id", target = "id")
    ChannelDto entityToDto(Channel channel);

    @Mapping(source = "id", target = "id")
    Channel dtoToEntity(ChannelDto channelDto);
}
