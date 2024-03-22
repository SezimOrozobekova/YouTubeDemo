package com.example.youtubedemo.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
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


    @NotBlank
    @Size(min = 1, max = 255)
    private String title;

    @NotBlank
    @Size(min = 1, max = 1000)
    private String description;

    @NotNull
    @PastOrPresent(message = "Upload date must be in the past or present")
    private LocalDateTime uploadDate;

    @NotNull
    private Duration duration;

    @NotNull
    private Long views;

    @NotNull
    @Column(updatable = false)
    private Long channelId;


}
