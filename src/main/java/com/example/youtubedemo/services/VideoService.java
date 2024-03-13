package com.example.youtubedemo.services;

import com.example.youtubedemo.Entity.Video;
import com.example.youtubedemo.dto.VideoDto;
import com.example.youtubedemo.repositories.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class VideoService {

    private final VideoRepository videoRepository; // Assuming you have a VideoRepository

    @Autowired
    public VideoService(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    // Create a new video
    public VideoDto createVideo(VideoDto videoDto) {
        // Convert VideoDto to Video entity
        Video video = new Video();
        video.setTitle(videoDto.getTitle());
        // Other fields assignment

        // Save the video entity
        Video savedVideo = videoRepository.save(video);

        // Convert saved Video entity back to VideoDto and return
        return mapToDto(savedVideo);
    }

    // Get a video by ID
    public VideoDto getVideoById(Long id) throws NoSuchElementException {
        // Find the video entity by ID
        Video video = videoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Video not found with ID: " + id));

        // Convert video entity to VideoDto and return
        return mapToDto(video);
    }

    // Update an existing video
    public VideoDto updateVideo(Long id, VideoDto videoDto) throws NoSuchElementException {
        // Find the video entity by ID
        Video video = videoRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);

        // Update the video entity with new values
        video.setTitle(videoDto.getTitle());
        // Other fields assignment

        // Save the updated video entity
        Video updatedVideo = videoRepository.save(video);

        // Convert updated Video entity back to VideoDto and return
        return mapToDto(updatedVideo);
    }


    public void deleteVideo(Long id) throws NoSuchElementException {
        if (!videoRepository.existsById(id)) {
            throw new NoSuchElementException("Video not found with ID: " + id);
        }
        videoRepository.deleteById(id);
    }

    // Helper method to map Video entity to VideoDto
    private VideoDto mapToDto(Video video) {
        VideoDto videoDto = new VideoDto();
        videoDto.setId(video.getId());
        videoDto.setTitle(video.getTitle());
        // Other fields mapping
        return videoDto;
    }

}