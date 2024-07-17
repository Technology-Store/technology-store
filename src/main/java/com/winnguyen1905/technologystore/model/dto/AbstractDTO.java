package com.winnguyen1905.technologystore.model.dto;

import java.io.Serializable;
import java.util.*;

import lombok.*;

@Builder
@Getter
@Setter
public class AbstractDTO<T> implements Serializable {

    private static final long serialVersionUID = 7213600440729202783L;

    private Long id;
    private Date createdDate;
    private String createdBy;
    private Date modifiedDate;
    private String modifiedBy;
    @Builder.Default
    private int maxPageItems = 2;
    @Builder.Default
    private int page = 1;
    @Builder.Default
    private List<T> listResult = new ArrayList<>();
    @Builder.Default
    private int totalItems = 0;
    @Builder.Default
    private String tableId = "tableList";
    private Integer limit;
    private Integer totalPage;
    private Integer totalItem;
    private String searchValue;
    
}