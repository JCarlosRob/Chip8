package com.chip8.configure;

import com.chip8.model.core.register.VRegisterHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Random;

@Configuration
public class AppConfigure {

    @Bean("vectorXRegister")
    public VRegisterHandler vectorXRegister() {
        return new VRegisterHandler();
    }

    @Bean("vectorYRegister")
    public VRegisterHandler vectorYRegister() {
        return new VRegisterHandler();
    }

    @Bean
    public Random random() {
        return new Random();
    }
}
