package com.example.youtubedemo.repositories;
import org.springframework.data.domain.Page;


import com.example.youtubedemo.Entity.Video;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;

@Repository
public interface VideoRepository extends JpaRepository<Video,Long> {


}
