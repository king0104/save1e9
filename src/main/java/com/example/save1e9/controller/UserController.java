package com.example.save1e9.controller;

import com.example.save1e9.entity.User;
import com.example.save1e9.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor // final 필드 값을 파라미터로 하는 생성자 생성
public class UserController {

    private final UserRepository userRepository;

    @PostMapping("/save")
    public void save() {
        long startTime = System.currentTimeMillis();

        for(int i=0;i<5000;i++) {
            userRepository.save(new User(null, "yoon", 1));
        }

        long stopTime = System.currentTimeMillis();

        long elapsedTime = stopTime - startTime;
        System.out.println(elapsedTime);
    }


}
