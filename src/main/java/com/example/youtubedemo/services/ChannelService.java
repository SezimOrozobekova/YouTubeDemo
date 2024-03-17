package com.example.youtubedemo.services;

import com.example.youtubedemo.Entity.Channel;
import com.example.youtubedemo.repositories.ChannelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ChannelService {

    private final ChannelRepository channelRepository;

    @Autowired
    public ChannelService(ChannelRepository channelRepository) {
        this.channelRepository = channelRepository;
    }

    public List<Channel> getAllChannels() {
        return channelRepository.findAll();
    }

    public Channel getChannelById(Long id) {
        return channelRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Channel not found with id: " + id));
    }

    public Channel createChannel(Channel channel) {
        return channelRepository.save(channel);
    }

    public Channel updateChannel(Long id, Channel updatedChannel) {
        if (!channelRepository.existsById(id)) {
            throw new NoSuchElementException("Channel not found with id: " + id);
        }
        updatedChannel.setId(id); // Ensure the correct ID is set for the updated channel
        return channelRepository.save(updatedChannel);
    }

    public void deleteChannel(Long id) {
        if (!channelRepository.existsById(id)) {
            throw new NoSuchElementException("Channel not found with id: " + id);
        }
        channelRepository.deleteById(id);
    }
}
