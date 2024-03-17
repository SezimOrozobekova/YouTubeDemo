package com.example.youtubedemo.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CommentDto {
    private Long id;
    @NotNull
    @Size(min = 1, max = 1000)
    private String text;
    private LocalDateTime createdAt;
    @NotNull
    private Long videoId;
}
