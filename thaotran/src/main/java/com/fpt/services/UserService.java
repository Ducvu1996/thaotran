package com.fpt.services;

import com.fpt.dto.UserDto;
import com.fpt.entities.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {
    List<UserDto> getAll();

    Page<User> findByUserNameLike(Integer pageNumber, Integer pageSize, String keyword);

    UserDto save(UserDto user);

    User update(User user, Integer id);

    boolean delete(Long id);

    UserDto findById(Long id);
}
