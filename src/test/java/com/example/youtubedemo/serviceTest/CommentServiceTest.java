package com.example.youtubedemo.serviceTest;

import com.example.youtubedemo.Entity.Comment;
import com.example.youtubedemo.dto.CommentDto;
import com.example.youtubedemo.mappers.CommentMapper;
import com.example.youtubedemo.repositories.CommentRepository;
import com.example.youtubedemo.services.CommentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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

    @Mock
    private CommentMapper commentMapper;

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
        List<CommentDto> result = commentService.getAllComments();

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
        comment.setText("Hallo");
        when(commentRepository.findById(id)).thenReturn(Optional.of(comment));

        // When
        CommentDto result = commentService.getCommentById(id);

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
        CommentDto commentDto = new CommentDto();
        Comment comment = new Comment();
        when(commentMapper.dtoToEntity(commentDto)).thenReturn(comment);
        when(commentRepository.save(comment)).thenReturn(comment);
        when(commentMapper.entityToDto(comment)).thenReturn(commentDto);

        // When
        CommentDto result = commentService.createComment(commentDto);

        // Then
        assertNotNull(result);
        verify(commentMapper, times(1)).dtoToEntity(commentDto);
        verify(commentRepository, times(1)).save(comment);
        verify(commentMapper, times(1)).entityToDto(comment);
    }

    @Test
    void testUpdateComment() {
        // Given
        Long id = 1L;
        CommentDto updatedCommentDto = new CommentDto();
        updatedCommentDto.setText("Updated text");
        Comment existingComment = new Comment();
        existingComment.setId(id);
        existingComment.setText("Old text");
        when(commentRepository.findById(id)).thenReturn(Optional.of(existingComment));
        when(commentRepository.save(existingComment)).thenReturn(existingComment);
        when(commentMapper.entityToDto(existingComment)).thenReturn(updatedCommentDto);

        // When
        CommentDto result = commentService.updateComment(id, updatedCommentDto);

        // Then
        assertNotNull(result);
        assertEquals(updatedCommentDto.getText(), result.getText());
        verify(commentRepository, times(1)).findById(id);
        verify(commentRepository, times(1)).save(existingComment);
        verify(commentMapper, times(1)).entityToDto(existingComment);
    }

    @Test
    void testDeleteComment() {
        // Given
        Long id = 1L;
        Comment comment = new Comment();
        when(commentRepository.existsById(id)).thenReturn(true);

        // When
        commentService.deleteComment(id);

        // Then
        verify(commentRepository, times(1)).deleteById(id);
    }
}
