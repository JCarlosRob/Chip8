package com.chip8.boot;

import com.chip8.model.core.instruction.SysCallInstruction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.chip8.*")
public class Main implements CommandLineRunner {

    @Autowired
    private SysCallInstruction syscall;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String...args) throws Exception {
        System.out.println("Application Started !!");
        System.out.println(syscall.getType());
        syscall.getBehavior().run();
    }

}