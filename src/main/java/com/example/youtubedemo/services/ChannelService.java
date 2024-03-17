package com.example.youtubedemo.services;

import com.example.youtubedemo.Entity.Channel;
import com.example.youtubedemo.dto.ChannelDto;
import com.example.youtubedemo.mappers.ChannelMapper;
import com.example.youtubedemo.repositories.ChannelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class ChannelService {

    private final ChannelRepository channelRepository;
    private final ChannelMapper channelMapper;

    @Autowired
    public ChannelService(ChannelRepository channelRepository, ChannelMapper channelMapper) {
        this.channelRepository = channelRepository;
        this.channelMapper = channelMapper;
    }

    public List<ChannelDto> getAllChannels() {
        List<Channel> channels = channelRepository.findAll();
        return channels.stream()
                .map(channelMapper::entityToDto)
                .collect(Collectors.toList());
    }

    public ChannelDto getChannelById(Long id) {
        Channel channel = channelRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Channel not found with id: " + id));
        return channelMapper.entityToDto(channel);
    }

    public ChannelDto createChannel(ChannelDto channelDto) {
        Channel channel = channelMapper.dtoToEntity(channelDto);
        Channel savedChannel = channelRepository.save(channel);
        return channelMapper.entityToDto(savedChannel);
    }

    public ChannelDto updateChannel(Long id, ChannelDto updatedChannelDto) {
        Channel existingChannel = channelRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Channel not found with id: " + id));

        Channel updatedChannel = channelMapper.dtoToEntity(updatedChannelDto);
        updatedChannel.setId(id); // Ensure the correct ID is set for the updated channel

        Channel savedChannel = channelRepository.save(updatedChannel);
        return channelMapper.entityToDto(savedChannel);
    }

    public void deleteChannel(Long id) {
        if (!channelRepository.existsById(id)) {
            throw new NoSuchElementException("Channel not found with id: " + id);
        }
        channelRepository.deleteById(id);
    }
}
