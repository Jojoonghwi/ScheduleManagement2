package com.sparta.schedulemanagement2.controller;

import com.sparta.schedulemanagement2.dto.CommentRequestDto;
import com.sparta.schedulemanagement2.dto.CommentResponseDto;
import com.sparta.schedulemanagement2.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comment")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/{id}")
    public CommentResponseDto createComment(@Valid @PathVariable Long id, @RequestBody CommentRequestDto requestDto) {
        return commentService.createComment(id, requestDto);
    }

    @GetMapping("")
    public List<CommentResponseDto> getComments() {
        return commentService.getComments();
    }

    @PutMapping("{id}")
    public Long updateComment(@PathVariable Long id, @RequestBody CommentRequestDto requestDto) {
        return commentService.updateComment(id, requestDto);
    }

    @DeleteMapping("{id}")
    public Long deleteComment(@PathVariable Long id) {
        return commentService.deleteComment(id);
    }
}
