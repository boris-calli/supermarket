package com.supermarket.demo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.supermarket.demo.dto.UserDto;
import com.supermarket.demo.model.entity.UserEntity;
import com.supermarket.demo.service.UserInterfaceService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {
    
    private final UserInterfaceService userInterfaceService;

    public UserController(UserInterfaceService userInterfaceService) {
        this.userInterfaceService = userInterfaceService;
    }

    @PostMapping("/register")
    public UserEntity registerNewUser(@Valid @RequestBody UserDto userDto) {
        return userInterfaceService.registerUser(userDto);
    }
}
