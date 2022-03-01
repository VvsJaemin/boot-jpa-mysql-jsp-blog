package com.cos.blog.repository;

import com.cos.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    // SELECT * FROM user WHERE = username = 1?;
    Optional<User> findByUsername(String username);

}


// JPA Naming 전략
// select * from user where username = ? and password = ?;
// User findByUsernameAndPassword(String username, String password);

//    @Query(value = "SELECT * FROM user WHERE username ?1 AND password = ?2 ", nativeQuery = true)
//    User login(String username, String password);