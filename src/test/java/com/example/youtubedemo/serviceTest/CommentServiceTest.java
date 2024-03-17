package com.example.youtubedemo.serviceTest;

import com.example.youtubedemo.Entity.Comment;
import com.example.youtubedemo.repositories.CommentRepository;
import com.example.youtubedemo.services.CommentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CommentServiceTest {

    @Mock
    private CommentRepository commentRepository;

    @InjectMocks
    private CommentService commentService;

    @Test
    void testGetAllComments() {
        // Given
        List<Comment> comments = new ArrayList<>();
        comments.add(new Comment());
        comments.add(new Comment());
        when(commentRepository.findAll()).thenReturn(comments);

        // When
        List<Comment> result = commentService.getAllComments();

        // Then
        assertEquals(comments.size(), result.size());
        verify(commentRepository, times(1)).findAll();
    }

    @Test
    void testGetCommentById() {
        // Given
        Long id = 1L;
        Comment comment = new Comment();
        comment.setId(id);
        when(commentRepository.findById(id)).thenReturn(Optional.of(comment));

        // When
        Comment result = commentService.getCommentById(id);

        // Then
        assertNotNull(result);
        assertEquals(id, result.getId());
        verify(commentRepository, times(1)).findById(id);
    }

    @Test
    void testGetCommentById_NotFound() {
        // Given
        Long id = 1L;
        when(commentRepository.findById(id)).thenReturn(Optional.empty());

        // When / Then
        assertThrows(NoSuchElementException.class, () -> commentService.getCommentById(id));
        verify(commentRepository, times(1)).findById(id);
    }

    @Test
    void testCreateComment() {
        // Given
        Comment comment = new Comment();
        when(commentRepository.save(comment)).thenReturn(comment);

        // When
        Comment result = commentService.createComment(comment);

        // Then
        assertNotNull(result);
        verify(commentRepository, times(1)).save(comment);
    }

    @Test
    void testUpdateComment() {
        // Given
        Long id = 1L;
        Comment existingComment = new Comment();
        existingComment.setId(id);
        existingComment.setText("Old Text");

        Comment updatedComment = new Comment();
        updatedComment.setText("New Text");

        when(commentRepository.findById(id)).thenReturn(Optional.of(existingComment));
        when(commentRepository.save(existingComment)).thenReturn(existingComment);

        // When
        Comment result = commentService.updateComment(id, updatedComment);

        // Then
        assertNotNull(result);
        assertEquals(updatedComment.getText(), result.getText());
        verify(commentRepository, times(1)).findById(id);
        verify(commentRepository, times(1)).save(existingComment);
    }

    @Test
    void testDeleteComment() {
        // Given
        Long id = 1L;
        Comment comment = new Comment();
        comment.setId(id);
        when(commentRepository.findById(id)).thenReturn(Optional.of(comment));

        // When
        commentService.deleteComment(id);

        // Then
        verify(commentRepository, times(1)).findById(id);
        verify(commentRepository, times(1)).delete(comment);
    }
}
