package com.example.youtubedemo.serviceTest;

import com.example.youtubedemo.Entity.Comment;
import com.example.youtubedemo.dto.CommentDto;
import com.example.youtubedemo.mappers.CommentMapper;
import com.example.youtubedemo.repositories.CommentRepository;
import com.example.youtubedemo.services.CommentService;
import com.example.youtubedemo.services.VideoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CommentServiceTest {

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private CommentMapper commentMapper;

    @Mock
    private VideoService videoService;

    @InjectMocks
    private CommentService commentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCommentExists() {
        // Arrange
        Long commentId = 1L;
        when(commentRepository.existsById(commentId)).thenReturn(true);

        // Act
        boolean result = commentService.commentExists(commentId);

        // Assert
        assertTrue(result);
    }

    @Test
    void testGetAllComments() {
        // Arrange
        List<Comment> comments = new ArrayList<>();
        comments.add(new Comment());
        when(commentRepository.findAll()).thenReturn(comments);

        CommentDto commentDto = new CommentDto();
        when(commentMapper.entityToDto(any(Comment.class))).thenReturn(commentDto);

        // Act
        List<CommentDto> result = commentService.getAllComments();

        // Assert
        assertEquals(1, result.size());
        assertEquals(commentDto, result.get(0));
    }

    @Test
    void testGetCommentById() {
        // Arrange
        Long commentId = 1L;
        Comment comment = new Comment();
        when(commentRepository.findById(commentId)).thenReturn(Optional.of(comment));

        CommentDto commentDto = new CommentDto();
        when(commentMapper.entityToDto(comment)).thenReturn(commentDto);

        // Act
        CommentDto result = commentService.getCommentById(commentId);

        // Assert
        assertNotNull(result);
        assertEquals(commentDto, result);
    }



    @Test
    void testDeleteComment() {
        // Arrange
        Long commentId = 1L;
        when(commentRepository.existsById(commentId)).thenReturn(true);

        // Act
        assertDoesNotThrow(() -> commentService.deleteComment(commentId));

        // Assert
        verify(commentRepository, times(1)).deleteById(commentId);
    }
}
