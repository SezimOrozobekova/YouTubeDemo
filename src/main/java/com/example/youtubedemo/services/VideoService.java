package com.example.youtubedemo.services;

import com.example.youtubedemo.Entity.Video;
import com.example.youtubedemo.dto.VideoDto;
import com.example.youtubedemo.mappers.VideoMapper;
import com.example.youtubedemo.repositories.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class VideoService {

    private final VideoRepository videoRepository;
    private final VideoMapper videoMapper;

    private static final int MAX_PAGE_SIZE = 1000;
    private static final int DEFAULT_PAGE_SIZE = 25;
    @Autowired
    public VideoService(VideoRepository videoRepository, VideoMapper videoMapper) {
        this.videoRepository = videoRepository;
        this.videoMapper = videoMapper;
    }

//    public List<VideoDto> getAllVideos() {
//        List<Video> videos = videoRepository.findAll();
//        return videos.stream()
//                .map(videoMapper::entityToDto)
//                .collect(Collectors.toList());
//    }


    public VideoDto createVideo(VideoDto videoDto) {
        try {
            Video video = videoMapper.dtoToEntity(videoDto);
            Video savedVideo = videoRepository.save(video);
            return videoMapper.entityToDto(savedVideo);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to create video: " + e.getMessage());
        }
    }

    public VideoDto getVideoById(Long id) throws NoSuchElementException {
        Video video = videoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Video not found with ID: " + id));
        return videoMapper.entityToDto(video);
    }


    public VideoDto updateVideo(Long id, VideoDto videoDto) {
        Video video = videoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Video not found with ID: " + id));


        video.setTitle(videoDto.getTitle());
        video.setDescription(videoDto.getDescription());
        video.setDuration(videoDto.getDuration());

        Video updatedVideo = videoRepository.save(video);

        return videoMapper.entityToDto(updatedVideo);
    }


    public void deleteVideo(Long id) throws NoSuchElementException {
        if (!videoRepository.existsById(id)) {
            throw new NoSuchElementException("Video not found with ID: " + id);
        }
        videoRepository.deleteById(id);
    }



}