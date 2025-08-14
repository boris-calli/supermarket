package com.supermarket.demo.service;

import java.util.List;

import com.supermarket.demo.dto.UserDto;
import com.supermarket.demo.model.entity.UserEntity;

public interface UserInterfaceService {
    UserEntity registerUser(UserDto userDto);
    List<UserEntity> fetchUserList();
    UserEntity fetchUser(Long id);
    UserEntity updateUser(Long id, UserDto userDto);
    void deleteUser(Long id);
}
