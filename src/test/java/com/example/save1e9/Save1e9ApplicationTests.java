package com.example.save1e9;

import com.example.save1e9.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@RequiredArgsConstructor
class Save1e9ApplicationTests {

    private final UserRepository userRepository;

    @Test
    void contextLoads() {
    }

}
