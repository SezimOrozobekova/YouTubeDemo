package com.example.youtubedemo.Entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

//    @Column(nullable = false)

    private String description;

    private LocalDateTime uploadDate;

    private Duration duration;

    private Long views;
//
//    @ManyToOne
//    private Channel channel;

}
