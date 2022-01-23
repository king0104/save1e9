package com.example.save1e9.service;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class Consumer {

    @KafkaListener(topics = "baeldung")
    public void listenGroupFoo(String message) {
        System.out.println("received message foo : " + message);
    }

}
