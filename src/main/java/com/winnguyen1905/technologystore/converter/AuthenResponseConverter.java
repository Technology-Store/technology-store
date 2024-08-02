package com.winnguyen1905.technologystore.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

import com.winnguyen1905.technologystore.entity.UserEntity;
import com.winnguyen1905.technologystore.model.dto.UserDTO;
import com.winnguyen1905.technologystore.model.response.AuthenResponse;

@Component
public class AuthenResponseConverter {
    @Autowired
    public ModelMapper modelMapper;

    public AuthenResponse toAuthenResponse(UserEntity user, Pair<String, String> tokenPair) {
        if (tokenPair == null)
            return AuthenResponse.builder().userDTO(modelMapper.map(user, UserDTO.class)).build();
        return AuthenResponse.builder()
                .userDTO(modelMapper.map(user, UserDTO.class))
                .accessToken(tokenPair.getFirst())
                .refreshToken(tokenPair.getSecond())
                .build();
    }
}