package com.winnguyen1905.technologystore.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.winnguyen1905.technologystore.entity.UserEntity;
import com.winnguyen1905.technologystore.model.dto.MyUserDetails;
import com.winnguyen1905.technologystore.repository.UserRepository;

@Transactional
@Component("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public CustomUserDetailsService(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = this.userRepository.findUserByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("Not found user by username " + username));
        return this.modelMapper.map(user, MyUserDetails.class);
    }
}