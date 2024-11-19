package com.winnguyen1905.technologystore.model.request;

import com.winnguyen1905.technologystore.model.dto.BaseObjectDTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class SearchPermissionRequest extends BaseObjectDTO<SearchPermissionRequest> {
    private String name;
    private String code;
    private String apiPath;
    private String method;
    private String module;
}