package com.example.youtubedemo.repositories;

import com.example.youtubedemo.Entity.Interaction;
import com.example.youtubedemo.dto.InteractionDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InteractionRepository extends JpaRepository<Interaction,Long> {
}
