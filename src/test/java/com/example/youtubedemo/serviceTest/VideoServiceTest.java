package com.example.youtubedemo.serviceTest;

import com.example.youtubedemo.Entity.Video;
import com.example.youtubedemo.dto.VideoDto;
import com.example.youtubedemo.mappers.VideoMapper;
import com.example.youtubedemo.repositories.VideoRepository;
import com.example.youtubedemo.services.VideoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class VideoServiceTest {

    @Mock
    private VideoRepository videoRepository;

    @Mock
    private VideoMapper videoMapper;

    @InjectMocks
    private VideoService videoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

//    @Test
//    void testGetAllVideos() {
//        // Given
//        List<Video> videos = new ArrayList<>();
//        videos.add(new Video());
//        videos.add(new Video());
//        when(videoRepository.findAll()).thenReturn(videos);
//
//        List<VideoDto> expectedVideoDtos = new ArrayList<>();
//        expectedVideoDtos.add(new VideoDto());
//        expectedVideoDtos.add(new VideoDto());
//        when(videoMapper.entityToDto(any(Video.class))).thenReturn(new VideoDto());
//
//        // When
//        List<VideoDto> result = videoService.getAllVideos();
//
//        // Then
//        assertEquals(expectedVideoDtos.size(), result.size());
//        verify(videoRepository, times(1)).findAll();
//        verify(videoMapper, times(2)).entityToDto(any(Video.class));
//    }

    @Test
    void testCreateVideo() {

        VideoDto videoDto = new VideoDto();
        Video video = new Video();
        when(videoMapper.dtoToEntity(videoDto)).thenReturn(video);
        when(videoRepository.save(video)).thenReturn(video);
        when(videoMapper.entityToDto(video)).thenReturn(videoDto);

        VideoDto result = videoService.createVideo(videoDto);

        assertNotNull(result);
        assertEquals(videoDto, result);
        verify(videoMapper, times(1)).dtoToEntity(videoDto);
        verify(videoRepository, times(1)).save(video);
        verify(videoMapper, times(1)).entityToDto(video);
    }

    @Test
    void testGetVideoById() {

        Long id = 1L;
        Video video = new Video();
        video.setId(id);
        when(videoRepository.findById(id)).thenReturn(Optional.of(video));
        VideoDto videoDto = new VideoDto();
        videoDto.setId(id);
        when(videoMapper.entityToDto(video)).thenReturn(videoDto);

        VideoDto result = videoService.getVideoById(id);

        assertNotNull(result);
        assertEquals(id, result.getId());
        verify(videoRepository, times(1)).findById(id);
        verify(videoMapper, times(1)).entityToDto(video);
    }



}
