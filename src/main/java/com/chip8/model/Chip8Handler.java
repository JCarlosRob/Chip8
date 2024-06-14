package com.chip8.model;

import com.chip8.api.Chip8;
import com.chip8.api.core.Cpu;
import com.chip8.api.rom.Loader;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class Chip8Handler implements Chip8 {

    private final Loader loader;

    private final Cpu cpu;


    public Chip8Handler(final Loader loader, final Cpu cpu) {
        this.loader = loader;
        this.cpu = cpu;
    }


    @Override
    public void start() throws IOException, InterruptedException {
        this.loader.load("src/main/resources/roms/PONG");
        this.cpu.run();
    }
}
