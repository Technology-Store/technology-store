package com.winnguyen1905.technologystore.model.dto;

import java.io.Serializable;
import java.util.*;

import lombok.*;

@Getter
@Setter
public class AbstractDTO<T> implements Serializable {

    private static final long serialVersionUID = 7213600440729202783L;

    private UUID id;
    private Date createdDate;
    private String createdBy;
    private Date modifiedDate;
    private String modifiedBy;
    private int maxPageItems = 2;
    
    private int page = 1;
    
    private List<T> listResult = new ArrayList<>();
    
    private int totalItems = 0;
    
    private String tableId = "tableList";
    private Integer limit;
    private Integer totalPage;
    private Integer totalItem;
    private String searchValue;

}