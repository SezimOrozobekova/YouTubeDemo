package com.example.youtubedemo.controller;

import com.example.youtubedemo.dto.VideoDto;
import com.example.youtubedemo.services.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    public ResponseEntity<VideoDto> getVideoById(@PathVariable Long id) {
        try {
            VideoDto videoDto = videoService.getVideoById(id);
            return new ResponseEntity<>(videoDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(ID_PATH)
    public ResponseEntity<Void> deleteVideo(@PathVariable Long id) {
        try {
            videoService.deleteVideo(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(VIDEO_PATH)
    public ResponseEntity<VideoDto> createVideo(@RequestBody VideoDto videoDto) {
        try {
            VideoDto savedVideoDto = videoService.createVideo(videoDto);
            return new ResponseEntity<>(savedVideoDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(ID_PATH)
    public ResponseEntity<VideoDto> updateVideo(@PathVariable Long id, @RequestBody VideoDto videoDto) {
        try {
            VideoDto updatedVideoDto = videoService.updateVideo(id, videoDto);
            return new ResponseEntity<>(updatedVideoDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
