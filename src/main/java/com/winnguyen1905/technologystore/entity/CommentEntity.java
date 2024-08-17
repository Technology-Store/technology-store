package com.winnguyen1905.technologystore.entity;

import java.util.ArrayList;
import java.util.List;

import com.winnguyen1905.technologystore.entity.base.BaseEntityAudit;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "comments")
public class CommentEntity extends BaseEntityAudit {

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_parent_id")
    private CommentEntity parentComment;

    @OneToMany(mappedBy = "parentComment", cascade = { CascadeType.ALL })
    private List<CommentEntity> ChildComments = new ArrayList<>();

    @Column(name = "comment_content", columnDefinition = "MEDIUMTEXT")
    private String content;
    
}