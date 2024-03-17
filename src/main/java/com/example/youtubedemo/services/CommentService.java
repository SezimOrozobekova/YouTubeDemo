package com.example.youtubedemo.services;

import com.example.youtubedemo.Entity.Comment;
import com.example.youtubedemo.dto.CommentDto;
import com.example.youtubedemo.mappers.CommentMapper;
import com.example.youtubedemo.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

    @Autowired
    public CommentService(CommentRepository commentRepository, CommentMapper commentMapper) {
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
    }

    public List<CommentDto> getAllComments() {
        List<Comment> comments = commentRepository.findAll();
        return comments.stream()
                .map(commentMapper::entityToDto)
                .collect(Collectors.toList());
    }

    public CommentDto getCommentById(Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Comment not found with id: " + id));
        return commentMapper.entityToDto(comment);
    }

    public CommentDto createComment(CommentDto commentDto) {
        Comment comment = commentMapper.dtoToEntity(commentDto);
        Comment savedComment = commentRepository.save(comment);
        return commentMapper.entityToDto(savedComment);
    }

    public CommentDto updateComment(Long id, CommentDto updatedCommentDto) {
        Comment existingComment = commentRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Comment not found with id: " + id));

        // Update fields
        existingComment.setText(updatedCommentDto.getText());
        // You can update other fields as needed

        Comment savedComment = commentRepository.save(existingComment);
        return commentMapper.entityToDto(savedComment);
    }

    public void deleteComment(Long id) {
        if (!commentRepository.existsById(id)) {
            throw new NoSuchElementException("Comment not found with id: " + id);
        }
        commentRepository.deleteById(id);
    }
}
