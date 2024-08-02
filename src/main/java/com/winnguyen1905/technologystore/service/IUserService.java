package com.winnguyen1905.technologystore.service;

import java.util.UUID;

import com.winnguyen1905.technologystore.model.dto.UserDTO;

public interface IUserService {
    public UserDTO handleGetUserByUsername(String username);

    public UserDTO handleGetUserById(UUID id);

    public UserDTO handleUpdateUser(UUID id);
}