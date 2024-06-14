package com.chip8.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Random;

@Configuration
public class AppConfigure {

    @Bean
    public Random random() {
        return new Random();
    }

}
