package com.supermarket.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.supermarket.demo.dto.UserDto;
import com.supermarket.demo.exception.ResourceExistsException;
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
    public UserEntity registerUser(UserDto userDto) {
        String username = userDto.getUsername().toUpperCase().trim();

        userRepository
        .findByUsername(username)
        .ifPresent(user -> {
            String message = String.format("El username %s ya existe", username);
            throw new ResourceExistsException(message, HttpStatus.CONFLICT);
        }); 

        UserEntity userEntity = new UserEntity();

        String password = userDto.getPassword();
        String name = userDto.getName();
        String lastName = userDto.getLastName();
        
        userEntity.setUsername(username);
        userEntity.setPassword(password);
        userEntity.setName(name);
        userEntity.setLastName(lastName);
        userEntity.setStatus(Boolean.TRUE);
        

        return userRepository.save(userEntity);
    }

    @Override
    public List<UserEntity> fetchUserList() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public UserEntity updateUser(Long id, UserDto userDto) {
        UserEntity userEntity = userRepository
        .findById(id)
        .orElseThrow(() -> {
            String message = "User Not Found in Database";
            return new ResourceExistsException(message, HttpStatus.BAD_REQUEST);
        });
        String username = userDto.getUsername();
        String password = userDto.getPassword();
        String name = userDto.getName();
        String lastName = userDto.getLastName();
        
        userEntity.setUsername(username);
        userEntity.setPassword(password);
        userEntity.setName(name);
        userEntity.setLastName(lastName);

        return userRepository.save(userEntity);
    }

    @Override
    public void deleteUser(Long id) {
        UserEntity userEntity = userRepository
        .findById(id)
        .orElseThrow(() -> {
            String message = "User Not Found in Database";
            return new ResourceExistsException(message, HttpStatus.BAD_REQUEST);
        });
        userRepository.delete(userEntity);
    }

    @Override
    public UserEntity fetchUser(Long id) {
        UserEntity userEntity = userRepository
        .findById(id)
        .orElseThrow(() -> {
            String message = "User Not Found in Database";
            return new ResourceExistsException(message, HttpStatus.BAD_REQUEST);
        });
        return userEntity;
    }

    @Override
    public Page<UserEntity> fetchUserPage(Pageable pageable) {
        return userRepository.findAll(pageable);
    }
}
