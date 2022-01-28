package com.example.save1e9.controller;

import com.example.save1e9.entity.User;
import com.example.save1e9.repository.UserRepository;
import com.example.save1e9.service.Producers;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final Producers producers;

    ExecutorService executor = Executors.newFixedThreadPool(4);

    @GetMapping("/send")
    public void sendMessage() {

        // 2. 3. worker 클래스(runnable) 만들기 by 람다식
        Runnable worker = () -> {
            try{

                for (int i = 0; i < 1e2; i++) {
                    long startTime = System.currentTimeMillis();

                    // service 패키지로 빼기?
                    User user = new User(null, "yoon", 1);
                    producers.sendMessage("baeldung",user);

                    long stopTime = System.currentTimeMillis();

                    long elapsedTime = stopTime - startTime;
                    System.out.println(elapsedTime);
                }

            }
            catch(Exception e) {
                e.printStackTrace();
            }
        };

        // 4. worker 클래스(runnable) submit
        for (int i = 0; i < 4; i++) {
            executor.submit(worker);
        }

    }




}

//@AllArgsConstructor
//class Worker implements Runnable {
//
//    private UserRepository userRepository;
//
//    public void run(){
//        try{
//
//            for (int i = 0; i < 1e2; i++) {
//                long startTime = System.currentTimeMillis();
//
//                userRepository.save(new User(null, "yoon", 1));
//
//                long stopTime = System.currentTimeMillis();
//
//                long elapsedTime = stopTime - startTime;
//                System.out.println(elapsedTime);
//            }
//
//        }
//        catch(Exception e) {
//            e.printStackTrace();
//        }
//    }
//}