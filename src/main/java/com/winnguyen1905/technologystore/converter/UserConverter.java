package com.winnguyen1905.technologystore.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.winnguyen1905.technologystore.entity.CustomerEntity;
import com.winnguyen1905.technologystore.entity.ShopEntity;
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

    public UserDTO toUserDTO(UserEntity user) {
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        return userDTO;
    }

    public CustomerEntity toCustomerEntity(UserEntity user) {
        CustomerEntity customer = this.modelMapper.map(user, CustomerEntity.class);
        return customer;
    }

    public ShopEntity toShopEntity(UserEntity user) {
        ShopEntity shop = this.modelMapper.map(user, ShopEntity.class);
        return shop;
    }
}