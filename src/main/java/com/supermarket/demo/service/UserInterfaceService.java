package com.supermarket.demo.service;

import com.supermarket.demo.dto.UserDto;
import com.supermarket.demo.model.entity.UserEntity;

public interface UserInterfaceService {
    UserEntity registerUser(UserDto userDTO);
}
