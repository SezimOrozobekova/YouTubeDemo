package com.example.youtubedemo.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class InteractionDto {
    private Long id;
    private boolean liked;
    private Long videoId;
}
