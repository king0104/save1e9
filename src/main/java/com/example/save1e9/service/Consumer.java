package com.example.save1e9.service;

import com.example.save1e9.entity.User;
import com.example.save1e9.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class Consumer {

    private final UserRepository userRepository;

    @KafkaListener(topics = "baeldung")
    public void listenGroupFoo(@Payload User user) {

        userRepository.save(user);

        System.out.println("received message foo : " + user.toString());
    }

}
