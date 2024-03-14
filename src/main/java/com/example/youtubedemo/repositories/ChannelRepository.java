package com.example.youtubedemo.repositories;

import com.example.youtubedemo.Entity.Channel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChannelRepository extends JpaRepository<Channel,Long> {
}
