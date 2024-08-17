package com.winnguyen1905.technologystore.service;

import java.util.UUID;

import org.springframework.data.domain.Pageable;

import com.winnguyen1905.technologystore.model.dto.CommentDTO;

public interface ICommentService {
    CommentDTO handleAddComment(CommentDTO commentDTO, UUID customerId);
    CommentDTO handleFetchCommentByParentComment(CommentDTO commentDTO, Pageable pageable);
    void handleDeleteComment(CommentDTO commentDTO, UUID customerId);
}