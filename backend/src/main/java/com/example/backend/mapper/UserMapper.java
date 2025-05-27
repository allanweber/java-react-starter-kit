package com.example.backend.mapper;

import org.springframework.stereotype.Component;

import com.example.backend.dto.UserDTO;
import com.example.backend.entity.User;

@Component
public class UserMapper {

    public UserDTO toDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        return dto;
    }

    public User toEntity(UserDTO dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        return user;
    }
}