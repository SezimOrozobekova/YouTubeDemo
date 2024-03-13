package com.example.youtubedemo.bootstrap;


import com.example.youtubedemo.Entity.Video;
import com.example.youtubedemo.repositories.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
@RequiredArgsConstructor
public class InitData implements CommandLineRunner {

    private final VideoRepository videoRepository;

    @Override
    public void run(String... args) {
        // Create videos
        Video video1 = Video.builder()
                .title("Introduction to Spring Boot")
                .duration(Duration.ofDays(1)) // Duration in minutes
                .build();

        Video video2 = Video.builder()
                .title("Java Basics")
                .duration(Duration.ofDays(1)) // Duration in minutes
                .build();

        // Save videos to the database
        videoRepository.save(video1);
        videoRepository.save(video2);
    }
}
