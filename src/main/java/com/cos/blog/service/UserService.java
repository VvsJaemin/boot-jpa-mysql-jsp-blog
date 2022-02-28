package com.cos.blog.service;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


// 스프링이 컴포넌트 스캔을 통해서 Bean에 등록을 해줌 IOC
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    private final BCryptPasswordEncoder encode;

    @Transactional
    public void userSave(User user) {

        String rawPassword = user.getPassword();
        String encPassword = encode.encode(rawPassword);

        user.setPassword(encPassword);

        user.setRole(RoleType.USER);
        userRepository.save(user);
    }


}
