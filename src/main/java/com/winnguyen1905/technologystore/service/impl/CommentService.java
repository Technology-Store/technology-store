package com.winnguyen1905.technologystore.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.winnguyen1905.technologystore.entity.CommentEntity;
import com.winnguyen1905.technologystore.entity.ProductEntity;
import com.winnguyen1905.technologystore.entity.UserEntity;
import com.winnguyen1905.technologystore.exception.CustomRuntimeException;
import com.winnguyen1905.technologystore.model.dto.CommentDTO;
import com.winnguyen1905.technologystore.repository.CommentRepository;
import com.winnguyen1905.technologystore.repository.ProductRepository;
import com.winnguyen1905.technologystore.repository.UserRepository;
import com.winnguyen1905.technologystore.service.ICommentService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentService implements ICommentService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final CommentRepository commentRepository;

    @Override
    public CommentDTO handleAddComment(CommentDTO commentDTO, UUID customerId) {
        UserEntity user = this.userRepository.findById(customerId)
                .orElseThrow(() -> new CustomRuntimeException("Not found user id " + customerId));
        ProductEntity product = this.productRepository.findById(commentDTO.getProduct().getId())
                .orElseThrow(() -> new CustomRuntimeException("Not found product id " + commentDTO.getProduct().getId()));
                
        commentDTO.setProduct(null);
        CommentEntity comment = this.modelMapper.map(commentDTO, CommentEntity.class);
        
        if(commentDTO.getParentComment() != null) {
            CommentEntity parentComment = this.commentRepository.findById(commentDTO.getParentComment().getId())
                    .orElseThrow(() -> new CustomRuntimeException("Not found parent comment id " + commentDTO.getParentComment().getId()));
            comment.setParentComment(parentComment);
        }

        comment.setUser(user);
        comment.setProduct(product);
        this.commentRepository.save(comment);

        return this.modelMapper.map(comment, CommentDTO.class);
    }

    @Override
    public CommentDTO handleFetchCommentByParentComment(CommentDTO commentDTO, Pageable pageable) {
        Page<CommentEntity> comment = this.commentRepository.findAllByParentCommentId(commentDTO.getId(), pageable);
        return this.modelMapper.map(comment, CommentDTO.class);
    }

    @Override
    public void handleDeleteComment(CommentDTO commentDTO, UUID customerId) {
        CommentEntity comment = this.commentRepository.findById(commentDTO.getId())
                .orElseThrow(() -> new CustomRuntimeException("Not found parent comment id " + commentDTO.getId()));
        if(!comment.getUser().getId().equals(customerId)) throw new CustomRuntimeException("Not found comment");
        this.commentRepository.delete(comment);
    }

}