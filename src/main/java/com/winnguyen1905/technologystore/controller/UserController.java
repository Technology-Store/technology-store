package com.winnguyen1905.technologystore.controller;

import org.springframework.web.bind.annotation.RestController;

import com.winnguyen1905.technologystore.entity.UserEntity;
import com.winnguyen1905.technologystore.service.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("${release.api.prefix}/users")
public class UserController {
    // @Autowired
    // private IUserService userService;

    // @GetMapping("/{username}")
    // public UserEntity getMethodName(
    //     @PathVariable("username") String username
    // ) {
    //     UserEntity user = userService.handlegetUserByUsername(username);
    //     return user;
    // }
}