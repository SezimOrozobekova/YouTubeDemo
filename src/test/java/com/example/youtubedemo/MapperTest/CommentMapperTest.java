package com.example.youtubedemo.MapperTest;

import com.example.youtubedemo.Entity.Comment;
import com.example.youtubedemo.Entity.Video;
import com.example.youtubedemo.dto.CommentDto;
import com.example.youtubedemo.mappers.CommentMapper;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CommentMapperTest {

    @Test
    void testEntityToDto() {
        // Create a Comment entity
        Comment comment = new Comment();
        comment.setId(1L);
        comment.setText("Comment Text");
        comment.setCreatedAt(LocalDateTime.now());
        comment.setVideo(new Video());

        // Use the mapper to convert Comment entity to CommentDto
        CommentDto commentDto = CommentMapper.INSTANCE.entityToDto(comment);

        // Check if the conversion is successful
        assertNotNull(commentDto);
        assertEquals(comment.getText(), commentDto.getText());
        assertEquals(comment.getCreatedAt(), commentDto.getCreatedAt());
        assertEquals(comment.getVideo().getId(), commentDto.getVideoId());
    }

    @Test
    void testDtoToEntity() {
        // Create a CommentDto
        CommentDto commentDto = new CommentDto();
        commentDto.setText("Comment Text");
        commentDto.setCreatedAt(LocalDateTime.now());
        commentDto.setVideoId(1L);

        // Use the mapper to convert CommentDto to Comment entity
        Comment comment = CommentMapper.INSTANCE.dtoToEntity(commentDto);

        // Check if the conversion is successful
        assertNotNull(comment);
        assertEquals(commentDto.getText(), comment.getText());
        assertEquals(commentDto.getCreatedAt(), comment.getCreatedAt());
        assertEquals(commentDto.getVideoId(), comment.getVideo().getId());
    }
}
