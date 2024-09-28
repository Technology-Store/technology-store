package com.winnguyen1905.technologystore.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.winnguyen1905.technologystore.exception.CustomRuntimeException;
import com.winnguyen1905.technologystore.model.dto.CommentDTO;
import com.winnguyen1905.technologystore.service.ICommentService;
import com.winnguyen1905.technologystore.util.SecurityUtils;

import lombok.RequiredArgsConstructor;

import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RestController
@RequiredArgsConstructor
@RequestMapping("${release.api.prefix}/comments")
public class CommentController {
    private final ICommentService commentService;

    @PostMapping
    public ResponseEntity<CommentDTO> addComment(@RequestBody CommentDTO commentDTO) {
        UUID customerId = SecurityUtils.getCurrentUserId()
                .orElseThrow(() -> new CustomRuntimeException("Not found user"));
        return ResponseEntity.status(HttpStatus.CREATED).body(this.commentService.handleAddComment(commentDTO, customerId));
    }

    @GetMapping("/")
    public ResponseEntity<CommentDTO> fetchCommentByParentComment(Pageable pageable, @RequestBody CommentDTO commentDTO) {
        return ResponseEntity.ok().body(this.commentService.handleFetchCommentByParentComment(commentDTO, pageable));
    }
    
    @DeleteMapping
    public ResponseEntity<Void> getMethodName(@RequestBody CommentDTO commentDTO) {
        UUID customerId = SecurityUtils.getCurrentUserId()
                .orElseThrow(() -> new CustomRuntimeException("Not found user"));
        this.commentService.handleDeleteComment(commentDTO, customerId);
        return ResponseEntity.noContent().build();
    }
}