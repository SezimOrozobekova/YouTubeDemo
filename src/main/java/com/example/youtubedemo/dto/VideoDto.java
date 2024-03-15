package com.example.youtubedemo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VideoDto {
    private Long id;
    private String title;
    private String description;
    private LocalDateTime uploadDate;
    private Duration duration;
    private Long views;
    private Long channelId;
}
