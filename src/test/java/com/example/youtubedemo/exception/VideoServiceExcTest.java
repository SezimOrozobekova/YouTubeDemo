package com.example.youtubedemo.exception;

import com.example.youtubedemo.advice.VideoNotFoundException;
import com.example.youtubedemo.dto.VideoDto;
import com.example.youtubedemo.repositories.VideoRepository;
import com.example.youtubedemo.mappers.VideoMapper;
import com.example.youtubedemo.services.VideoService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class VideoServiceExcTest {

    @Mock
    private VideoRepository videoRepository;

    @Mock
    private VideoMapper videoMapper;

    @InjectMocks
    private VideoService videoService;

    public VideoServiceExcTest() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void testGetVideoById_WhenVideoDoesNotExist() {
        Long videoId = 1L;
        when(videoRepository.findById(videoId)).thenReturn(Optional.empty());
        assertThrows(VideoNotFoundException.class, () -> videoService.getVideoById(videoId));
    }

    @Test
    public void testCreateVideo_WhenVideoDtoIsNull() {
        assertThrows(IllegalArgumentException.class, () -> videoService.createVideo(null));
    }

    @Test
    public void testUpdateVideo_WhenVideoDoesNotExist() {
        Long videoId = 1L;
        VideoDto videoDto = new VideoDto();
        when(videoRepository.existsById(videoId)).thenReturn(false);

        assertThrows(VideoNotFoundException.class, () -> videoService.updateVideo(videoId, videoDto));
    }

    @Test
    public void testDeleteVideo_WhenVideoDoesNotExist() {
        Long videoId = 1L;
        when(videoRepository.existsById(videoId)).thenReturn(false);

        assertThrows(VideoNotFoundException.class, () -> videoService.deleteVideo(videoId));
    }

    // Add more tests for other methods to ensure consistent exception handling

}
