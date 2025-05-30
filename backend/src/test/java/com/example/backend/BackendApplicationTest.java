package com.example.backend;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import com.example.backend.config.TestContainersConfig;

@Import(TestContainersConfig.class)
@SpringBootTest
public class BackendApplicationTest {
    @Test
    void testMain() {

    }
}
