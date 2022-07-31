package com.fpt.repository;

import com.fpt.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserName(String username);

    @Query(value = "select u from User u where u.userName like %?1%",
            countQuery = "select count(u) from User u where u.userName like %?1%"
    )
    Page<User> findByUserNameLike(String userName, Pageable pageable);
}
