package com.winnguyen1905.technologystore.model.request;

import com.winnguyen1905.technologystore.model.dto.AbstractDTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class PermissionSearchRequest extends AbstractDTO<PermissionSearchRequest> {
    private String name;
    private String code;
    private String apiPath;
    private String method;
    private String module;
}