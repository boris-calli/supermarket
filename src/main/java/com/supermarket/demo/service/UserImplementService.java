package com.supermarket.demo.service;

import org.springframework.stereotype.Service;

import com.supermarket.demo.dto.UserDto;
import com.supermarket.demo.model.entity.UserEntity;
import com.supermarket.demo.model.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserImplementService implements UserInterfaceService {

    private final UserRepository userRepository;

    public UserImplementService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserEntity registerUser(UserDto userDTO) {
        userRepository.findByUsername(userDTO.getUsername()).ifPresent(user -> { throw new IllegalArgumentException("El username ya existe"); });

        UserEntity userEntity = new UserEntity();
        userEntity.setName(userDTO.getName());
        userEntity.setLastName(userDTO.getLastName());
        userEntity.setUsername(userDTO.getUsername());
        userEntity.setPassword(userDTO.getPassword());
        userEntity.setStatus(true);

        return userRepository.save(userEntity);
    }
    
}
