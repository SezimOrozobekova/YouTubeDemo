package com.example.youtubedemo.controller;

import com.example.youtubedemo.dto.VideoDto;
import com.example.youtubedemo.services.VideoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class VideoControllerTest {

    @Mock
    VideoService videoService;

    @InjectMocks
    VideoController videoController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetVideoById() {
        Long videoId = 1L;
        VideoDto expectedVideoDto = new VideoDto();
        when(videoService.getVideoById(videoId)).thenReturn(expectedVideoDto);

        ResponseEntity<?> responseEntity = videoController.getVideoById(videoId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedVideoDto, responseEntity.getBody());
    }

    @Test
    void testGetAllVideos() {
        List<VideoDto> expectedVideoList = new ArrayList<>();
        when(videoService.getAllVideos()).thenReturn(expectedVideoList);

        List<VideoDto> result = videoController.getAllVideos();

        assertEquals(expectedVideoList, result);
    }

    @Test
    void testDeleteVideo() {
        Long videoId = 1L;
        ResponseEntity<Void> expectedResponseEntity = ResponseEntity.noContent().build();

        ResponseEntity<Void> result = videoController.deleteVideo(videoId);

        assertEquals(expectedResponseEntity, result);
        verify(videoService, times(1)).deleteVideo(videoId);
    }

    @Test
    void testCreateVideo() {
        VideoDto videoDto = new VideoDto();
        VideoDto savedVideoDto = new VideoDto();
        when(videoService.createVideo(videoDto)).thenReturn(savedVideoDto);

        ResponseEntity<?> responseEntity = videoController.createVideo(videoDto);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(savedVideoDto, responseEntity.getBody());
    }

    @Test
    void testUpdateVideo() {
        Long videoId = 1L;
        VideoDto videoDto = new VideoDto();
        VideoDto updatedVideoDto = new VideoDto();
        when(videoService.updateVideo(videoId, videoDto)).thenReturn(updatedVideoDto);

        ResponseEntity<VideoDto> responseEntity = videoController.updateVideo(videoId, videoDto);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(updatedVideoDto, responseEntity.getBody());
    }
}
