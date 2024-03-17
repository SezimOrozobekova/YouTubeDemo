package com.example.youtubedemo.dto;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ChannelDto {
    private Long id;
    @NotNull
    @Size(min = 1, max = 50)
    private String name;
    @Size(min = 5, max = 50)
    private String avatar;

}
