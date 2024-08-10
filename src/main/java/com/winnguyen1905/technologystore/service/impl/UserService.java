package com.winnguyen1905.technologystore.service.impl;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.winnguyen1905.technologystore.entity.UserEntity;
import com.winnguyen1905.technologystore.model.dto.UserDTO;
import com.winnguyen1905.technologystore.repository.UserRepository;
import com.winnguyen1905.technologystore.service.IUserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public UserDTO handleGetUserByUsername(String username) {
        UserEntity user = this.userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Not found user by username " + username));
        return this.modelMapper.map(user, UserDTO.class);
    }

    @Override
    public UserDTO handleGetUserById(UUID id) {
        UserEntity user = this.userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("Not found user by id " + id));
        return this.modelMapper.map(user, UserDTO.class);
    }

    @Override
    public UserDTO handleUpdateUser(UUID id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'handleUpdateUser'");
    }
}