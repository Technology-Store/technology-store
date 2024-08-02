package com.winnguyen1905.technologystore.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.winnguyen1905.technologystore.entity.UserEntity;
import com.winnguyen1905.technologystore.model.dto.UserDTO;
import com.winnguyen1905.technologystore.model.request.RegisterRequest;

@Component
public class UserConverter {
    @Autowired
    public ModelMapper modelMapper;

    public <T> UserEntity toUserEntity(T object) {
        UserEntity user = new UserEntity();
        if(object instanceof RegisterRequest registerRequest) {
            user = modelMapper.map(registerRequest, UserEntity.class);
        } else {

        }
        return user;
    }

    public UserDTO toUserDTO(UserEntity userEntity) {
        UserDTO userDTO = modelMapper.map(userEntity, UserDTO.class);
        return userDTO;
    }
}