package com.supermarket.demo.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.supermarket.demo.model.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    
    Optional<UserEntity> findByUsername(String username);
}
