package com.example.save1e9.controller;


import com.example.save1e9.service.Producers;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/kafka")
public class KafkaController {

    private final Producers producers;

    @GetMapping("/{message}")
    public String sendMessage(@PathVariable("message") String message) {
        producers.sendMessage("baeldung",message);

        return message;
    }
}
