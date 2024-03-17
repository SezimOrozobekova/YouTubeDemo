package com.example.youtubedemo.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @NotNull
    @Size(min = 1, max = 255)
    private String title;

    @NotNull
    @Size(min = 1, max = 1000)
    private String description;

    @NotNull
    private LocalDateTime uploadDate;

    @NotNull
    private Duration duration;

    @NotNull
    private Long views;

    @NotNull
    private Long channelId;
}
