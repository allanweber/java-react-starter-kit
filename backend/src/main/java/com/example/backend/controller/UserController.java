package com.example.backend.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.dto.UserDTO;
import com.example.backend.entity.User;
import com.example.backend.mapper.UserMapper;
import com.example.backend.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/users")
@Tag(name = "User Controller", description = "REST controller for User entity")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @GetMapping
    @Operation(summary = "Get all users", description = "Returns a list of all users")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved users")
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers().stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get user by ID", description = "Returns a user by their ID")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved user")
    @ApiResponse(responseCode = "404", description = "User not found")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(userMapper.toDTO(user));
    }

    @PostMapping
    @Operation(summary = "Create a new user", description = "Creates a new user")
    @ApiResponse(responseCode = "201", description = "User created successfully")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        User user = userMapper.toEntity(userDTO);
        User savedUser = userService.createUser(user);
        return ResponseEntity.ok(userMapper.toDTO(savedUser));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a user", description = "Updates a user by their ID")
    @ApiResponse(responseCode = "200", description = "User updated successfully")
    @ApiResponse(responseCode = "404", description = "User not found")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        User user = userMapper.toEntity(userDTO);
        User updatedUser = userService.updateUser(id, user);
        return ResponseEntity.ok(userMapper.toDTO(updatedUser));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a user", description = "Deletes a user by their ID")
    @ApiResponse(responseCode = "204", description = "User deleted successfully")
    @ApiResponse(responseCode = "404", description = "User not found")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}