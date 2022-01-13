package com.example.save1e9.controller;

import com.example.save1e9.entity.User;
import com.example.save1e9.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ThreadController {
    private final UserRepository userRepository;

    @PostMapping("thread")
    public void usingThread(){

        class MultiThread extends Thread {
            MultiThread(){
            }

            public void run(){
                try{

                    for (int i = 0; i < 1000; i++) {
                        userRepository.save(new User(null, "yoon", 1));
                    }

                }
                catch(Exception e) {
                    e.printStackTrace();
                }
            }

        }

        MultiThread[] mt = new MultiThread[5];
        for (int i = 0; i < 5; i++) {
            mt[i] = new MultiThread();
            mt[i].start();
        }

        long startTime = System.currentTimeMillis();

        for (int i = 0; i < 5; i++) {
            try {
                mt[i].join();
            } catch (InterruptedException e) {}

        }

        long stopTime = System.currentTimeMillis();

        long elapsedTime = stopTime - startTime;
        System.out.println(elapsedTime);

    }

}