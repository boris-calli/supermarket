package com.supermarket.demo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.supermarket.demo.dto.UserDto;
import com.supermarket.demo.model.entity.UserEntity;
import com.supermarket.demo.service.UserInterfaceService;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/api")
public class UserController {
    
    private final UserInterfaceService userInterfaceService;

    public UserController(UserInterfaceService userInterfaceService) {
        this.userInterfaceService = userInterfaceService;
    }

    @PostMapping("/users")
    public UserEntity registerNewUser(@Valid @RequestBody UserDto userDto) {
        return userInterfaceService.registerUser(userDto);
    }

    @GetMapping("/users")
    public List<UserEntity> fetchUserList() {
        return userInterfaceService.fetchUserList();
    }

    @GetMapping("/users/{id}")
    public UserEntity fetchUser(@PathVariable Long id) {
        return userInterfaceService.fetchUser(id);
    }

    @PutMapping("/users/{id}")
    public UserEntity updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        return userInterfaceService.updateUser(id, userDto);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Long id) {
        userInterfaceService.deleteUser(id);
    }
    
}
