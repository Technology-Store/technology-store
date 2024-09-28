package com.winnguyen1905.technologystore.service;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;

import com.winnguyen1905.technologystore.model.dto.PermissionDTO;
import com.winnguyen1905.technologystore.model.request.SearchPermissionRequest;

public interface IPermissionService {
    PermissionDTO handleGetPermissions(SearchPermissionRequest permissionSearchRequest, Pageable pageable);

    PermissionDTO handleGetPermissionById(UUID id);

    PermissionDTO handleCreatePermission(PermissionDTO permissionDTO);

    PermissionDTO handleUpdatePermission(PermissionDTO permissionDTO);

    void handleDeletePermission(List<UUID> ids);
}