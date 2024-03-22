package com.example.youtubedemo.exception;

import com.example.youtubedemo.Entity.Comment;
import com.example.youtubedemo.dto.CommentDto;
import com.example.youtubedemo.repositories.CommentRepository;
import com.example.youtubedemo.services.CommentService;
import com.example.youtubedemo.services.VideoService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class CommentServiceTest {

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private VideoService videoService;

    @InjectMocks
    private CommentService commentService;



    @Test
    void testGetCommentById_NonExistingComment() {
        // Arrange
        long nonExistingCommentId = 999L;
        when(commentRepository.findById(nonExistingCommentId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NoSuchElementException.class, () -> {
            commentService.getCommentById(nonExistingCommentId);
        });
    }


    @Test
    void testDeleteComment_NonExistingComment() {
        // Arrange
        long nonExistingCommentId = 999L;
        when(commentRepository.existsById(nonExistingCommentId)).thenReturn(false);

        // Act & Assert
        assertThrows(NoSuchElementException.class, () -> {
            commentService.deleteComment(nonExistingCommentId);
        });
    }
}
