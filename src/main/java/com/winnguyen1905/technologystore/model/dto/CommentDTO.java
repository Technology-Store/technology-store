package com.winnguyen1905.technologystore.model.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDTO extends BaseObjectDTO<CommentDTO> {

    private ProductDTO product;

    private UserDTO user;

    private CommentDTO parentComment;

    private List<CommentDTO> ChildComments;

    private String description;

    private Integer left;

    private Integer right;

}