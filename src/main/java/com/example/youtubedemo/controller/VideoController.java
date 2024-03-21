package com.example.youtubedemo.controller;

import com.example.youtubedemo.dto.VideoDto;
import com.example.youtubedemo.services.VideoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class VideoController {

    private final String API_PATH = "/api/v1/";
    private final String VIDEO_PATH = API_PATH + "video";
    private final String ID_PATH = VIDEO_PATH + "/{id}";

    private final VideoService videoService;

    @Autowired
    public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }

    @GetMapping(ID_PATH)
    public ResponseEntity<?> getVideoById(@PathVariable Long id){
        VideoDto videoDto = videoService.getVideoById(id);
        return new ResponseEntity<>(videoDto, HttpStatus.OK);
    }

    @GetMapping(VIDEO_PATH)
    public List<VideoDto> getAllVideos() {
        return videoService.getAllVideos();
    }

    @DeleteMapping(ID_PATH)
    public ResponseEntity<Void> deleteVideo(@PathVariable Long id) {
        videoService.deleteVideo(id);
        return ResponseEntity.noContent().build();
    }
    @PostMapping(VIDEO_PATH)
    public ResponseEntity<?> createVideo(@Valid @RequestBody VideoDto videoDto) {
        VideoDto savedVideoDto = videoService.createVideo(videoDto);
        return new ResponseEntity<>(savedVideoDto, HttpStatus.CREATED);
    }

    @PutMapping(ID_PATH)
    public ResponseEntity<VideoDto> updateVideo(@PathVariable Long id, @Valid @RequestBody VideoDto videoDto) {
        VideoDto updatedVideoDto = videoService.updateVideo(id, videoDto);
        return new ResponseEntity<>( updatedVideoDto, HttpStatus.OK);
    }

}
