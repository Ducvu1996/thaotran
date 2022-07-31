package com.fpt.convert;

import com.fpt.dto.UserDto;
import com.fpt.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class UserConverter {
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserDto convertToDTO(User user) {
        UserDto userDto = new UserDto();
        userDto.setUserId(user.getUserId());
        userDto.setEmail(user.getEmail());
        userDto.setUserName(user.getUserName());
        userDto.setRole(user.getRole());
        userDto.setFullName(user.getFullName());
        userDto.setPassword(user.getPassword());
        return userDto;
    }

    public User convertToEntity(UserDto userDto) {
        User user = new User();
        user.setUserId(userDto.getUserId());
        user.setEmail(userDto.getEmail());
        user.setUserName(userDto.getUserName());
        user.setFullName(userDto.getFullName());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRole(userDto.getRole());
        user.setCreatedAt(Instant.now());
        user.setUpdateAt(Instant.now());
        return user;
    }
}
