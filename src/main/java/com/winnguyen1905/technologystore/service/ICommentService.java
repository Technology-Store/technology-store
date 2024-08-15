package com.winnguyen1905.technologystore.service;

import java.util.UUID;

import com.winnguyen1905.technologystore.model.dto.CommentDTO;

public interface ICommentService {
    CommentDTO handleAddComment(CommentDTO commentDTO, UUID customerId);
}