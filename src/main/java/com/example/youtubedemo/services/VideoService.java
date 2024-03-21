package com.example.youtubedemo.services;

import com.example.youtubedemo.Entity.Channel;
import com.example.youtubedemo.Entity.Video;
import com.example.youtubedemo.dto.VideoDto;
import com.example.youtubedemo.advice.VideoNotFoundException;
import com.example.youtubedemo.mappers.VideoMapper;
import com.example.youtubedemo.repositories.ChannelRepository;
import com.example.youtubedemo.repositories.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VideoService {

    private final VideoRepository videoRepository;
    private final VideoMapper videoMapper;

    private final ChannelRepository channelRepository;
    @Autowired
    public VideoService(VideoRepository videoRepository, VideoMapper videoMapper, ChannelRepository channelRepository) {
        this.videoRepository = videoRepository;
        this.videoMapper = videoMapper;
        this.channelRepository = channelRepository;
    }

    public List<VideoDto> getAllVideos() throws DataAccessException {
        List<Video> videos = videoRepository.findAll();
        return videos.stream()
                .map(videoMapper::entityToDto)
                .collect(Collectors.toList());
    }

    public VideoDto createVideo(VideoDto videoDto) {
        try {
            Channel channel = channelRepository.findById(videoDto.getChannelId())
                    .orElseThrow(() -> new IllegalArgumentException("Channel with ID " + videoDto.getChannelId() + " does not exist"));
            Video video = videoMapper.dtoToEntity(videoDto);
            Video savedVideo = videoRepository.save(video);
            return videoMapper.entityToDto(savedVideo);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create video: " + e.getMessage());
        }
    }

    public VideoDto getVideoById(Long id) throws VideoNotFoundException{
        Video video = videoRepository.findById(id)
                .orElseThrow(() -> new VideoNotFoundException("Video not found with ID: " + id));
        return videoMapper.entityToDto(video);
    }



    public VideoDto updateVideo(Long id, VideoDto videoDto) throws VideoNotFoundException{
        Video video = videoRepository.findById(id)
                .orElseThrow(() -> new VideoNotFoundException("Video not found with ID: " + id));
        video.setTitle(videoDto.getTitle());
        video.setDescription(videoDto.getDescription());
        video.setDuration(videoDto.getDuration());
        Video updatedVideo = videoRepository.save(video);
        return videoMapper.entityToDto(updatedVideo);
    }


    public void deleteVideo(Long id) throws VideoNotFoundException {
        if (!videoRepository.existsById(id)) {
            throw new VideoNotFoundException("Video not found with ID: " + id);
        }
        videoRepository.deleteById(id);
    }



}