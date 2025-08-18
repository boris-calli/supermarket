package com.supermarket.demo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.supermarket.demo.dto.UserDto;
import com.supermarket.demo.model.entity.UserEntity;
import com.supermarket.demo.service.UserInterfaceService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;





@RestController
@RequestMapping("/api")
public class UserController {
    
    private final UserInterfaceService userInterfaceService;

    public UserController(UserInterfaceService userInterfaceService) {
        this.userInterfaceService = userInterfaceService;
    }

    @Operation(summary = "Register a new user", description = "Register a new user with all information")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Resource successfully created"),
        @ApiResponse(responseCode = "409", description = "Resource exists in the database")
    })
    @PostMapping("/users")
    public UserEntity registerNewUser(@Valid @RequestBody UserDto userDto) {
        return userInterfaceService.registerUser(userDto);
    }

    @Operation(summary = "Fetch all users", description = "Fetch all users exist in database")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Resources succesfully retrieved"),
    })
    @GetMapping("/users/all")
    public List<UserEntity> fetchUserList() {
        return userInterfaceService.fetchUserList();
    }

    @Operation(summary = "Fetch user by id", description = "Fetch a user from database by id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Resource succesfully retrieved"),
        @ApiResponse(responseCode = "400", description = "Resource not found")
    })
    @GetMapping("/users/{id}")
    public UserEntity fetchUser(@PathVariable Long id) {
        return userInterfaceService.fetchUser(id);
    }

    @Operation(summary = "Fetch users by page", description = "Fetch users from database by pages")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Resource succesfully retrieved"),
    })
    @GetMapping("/users/page")
    public Page<UserEntity> fetchUserPage(
        @RequestParam(value = "page", defaultValue = "0") int page,
        @RequestParam(value = "size", defaultValue = "10") int size,
        @RequestParam(value = "sort", required = false, defaultValue = "id") String sort,
        @RequestParam(value = "direction", required = false, defaultValue = "ASC") String direction
        ) {

        Sort.Order order = new Sort.Order(
            Sort.Direction.fromString(direction.toUpperCase()),
            sort
        );

        Pageable pageable = PageRequest.of(page, size, Sort.by(order));
        return userInterfaceService.fetchUserPage(pageable);
    }

    @Operation(summary = "Update user by id", description = "Update a user from database by id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Resource succesfully updated"),
        @ApiResponse(responseCode = "400", description = "Resource not found")
    })
    @PutMapping("/users/{id}")
    public UserEntity updateUser(@PathVariable Long id, @Valid @RequestBody UserDto userDto) {
        return userInterfaceService.updateUser(id, userDto);
    }

    @Operation(summary = "Delete user by id", description = "Delete a user from database by id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Resource succesfully deleted"),
        @ApiResponse(responseCode = "400", description = "Resource not found")
    })
    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Long id) {
        userInterfaceService.deleteUser(id);
    }
    
}
