package com.fpt.services.impl;

import com.fpt.convert.UserConverter;
import com.fpt.dto.UserDto;
import com.fpt.entities.User;
import com.fpt.repository.UserRepository;
import com.fpt.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserConverter appUserConverter;

    @Override
    public List<UserDto> getAll() {
        List<User> appUsers = userRepository.findAll();
        List<UserDto> appUserDtoList = new ArrayList<>();
        for (User appUser :
                appUsers) {
            appUserDtoList.add(appUserConverter.convertToDTO(appUser));
        }
        return appUserDtoList;
    }

    @Override
    public Page<User> findByUserNameLike(Integer pageNumber, Integer pageSize, String keyword) {
        if (pageNumber == null) pageNumber = 0;
        if (pageSize == null) pageSize = 5;
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        if (keyword == null) keyword = "";
        return userRepository.findByUserNameLike(keyword, pageable);
    }

    @Override
    public UserDto save(UserDto userDto) {
        User appUser = appUserConverter.convertToEntity(userDto);
        User appUserSave = userRepository.save(appUser);
        return appUserConverter.convertToDTO(appUserSave);
    }

    @Override
    public User update(User user, Integer id) {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        try {
            userRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public UserDto findById(Long id) {
        User appUser = userRepository.getById(id);
        return appUserConverter.convertToDTO(appUser);
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<User> user = Optional.ofNullable(userRepository.findByUserName(userName));
        if (!user.isPresent()) {
            log.error("User not found in the database");
            throw new UsernameNotFoundException("User not found in the database");
        } else {
            log.info("User found in the database: {}", userName);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.get().getRole()));
        return new org.springframework.security.core.userdetails.User(user.get().getUserName(), user.get().getPassword(), authorities);
    }
}
