package com.example.youtubedemo.controller;

import com.example.youtubedemo.dto.ChannelDto;
import com.example.youtubedemo.dto.VideoDto;
import com.example.youtubedemo.services.ChannelService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class ChannelController {
    private final String API_PATH = "/api/v1/";
    private final String CHANNEL_PATH = API_PATH + "channel";
    private final String ID_PATH = CHANNEL_PATH + "/{id}";

    private final ChannelService channelService;

    public ChannelController(ChannelService channelService) {
        this.channelService = channelService;
    }

    @GetMapping(ID_PATH)
    public ResponseEntity<ChannelDto> getVideoById(@PathVariable Long id) {
            ChannelDto channelDto = channelService.getChannelById(id);
            return new ResponseEntity<>(channelDto, HttpStatus.OK);
    }
    @GetMapping(CHANNEL_PATH)
    public ResponseEntity<List<ChannelDto>> getAllChannels() {
            List<ChannelDto> channels = channelService.getAllChannels();
            return new ResponseEntity<>(channels, HttpStatus.OK);
    }
    @PostMapping(CHANNEL_PATH)
    public ResponseEntity<ChannelDto> createChannel(@Valid @RequestBody ChannelDto channelDto) {
        ChannelDto createdChannelDto = channelService.createChannel(channelDto);
        return new ResponseEntity<>(createdChannelDto, HttpStatus.CREATED);

    }
    @PutMapping(ID_PATH)
    public ResponseEntity<ChannelDto> updateChannel(@PathVariable Long id, @Valid @RequestBody ChannelDto updatedChannelDto) {
        ChannelDto updatedChannel = channelService.updateChannel(id, updatedChannelDto);
        return new ResponseEntity<>(updatedChannel, HttpStatus.OK);
    }
    @DeleteMapping(ID_PATH)
    public ResponseEntity<Void> deleteChannel(@PathVariable Long id) {
        channelService.deleteChannel(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
