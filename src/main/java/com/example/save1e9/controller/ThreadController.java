package com.example.save1e9.controller;

import com.example.save1e9.entity.User;
import com.example.save1e9.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
@RequiredArgsConstructor
public class ThreadController {

    private final UserRepository userRepository;

    @PostMapping("thread")
    public void usingThread(){

        ExecutorService executor = Executors.newFixedThreadPool(4);

        Worker worker = new Worker(userRepository);
        for (int i = 0; i < 4; i++) {
            executor.submit(worker);
        }

    }

}

@AllArgsConstructor
class Worker implements Runnable {

    private UserRepository userRepository;

    public void run(){
        try{

            for (int i = 0; i < 1e2; i++) {
                long startTime = System.currentTimeMillis();

                userRepository.save(new User(null, "yoon", 1));

                long stopTime = System.currentTimeMillis();

                long elapsedTime = stopTime - startTime;
                System.out.println(elapsedTime);
            }

        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}