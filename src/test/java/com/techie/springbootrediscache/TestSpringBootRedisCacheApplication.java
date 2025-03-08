package com.techie.springbootrediscache;

import org.springframework.boot.SpringApplication;

public class TestSpringBootRedisCacheApplication {

    public static void main(String[] args) {
        SpringApplication.from(SpringBootRedisCacheApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
