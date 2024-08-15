package com.winnguyen1905.technologystore.service.impl;

import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
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

        } else {
            CommentEntity lastestParentComment = this.commentRepository.findOneWithHighestRightAndProductId(product.getId());
            if(lastestParentComment != null) {
                comment.setLeft(lastestParentComment.getRight() + 1);
                comment.setRight(lastestParentComment.getRight() + 2);
            } else {
                comment.setLeft(1);
                comment.setRight(2);
            }
        }

        comment.setUser(user);
        comment.setProduct(product);
        this.commentRepository.save(comment);

        return this.modelMapper.map(comment, CommentDTO.class);
    }

}