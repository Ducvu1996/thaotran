package com.fpt.repository;


import com.fpt.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<User, Long> {
    User findByUserName(String userName);
}
