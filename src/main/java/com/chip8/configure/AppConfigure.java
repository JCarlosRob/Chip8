package com.chip8.configure;

import com.chip8.api.core.memory.Memory;
import com.chip8.model.core.memory.MemoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Random;

@Configuration
public class AppConfigure {

    @Bean
    public Random random() {
        return new Random();
    }

    @Bean
    public Memory memoryRam() {
        return new MemoryImpl(4094);
    }

    @Bean
    public Memory memoryStack() {
        return new MemoryImpl(20);
    }

}
