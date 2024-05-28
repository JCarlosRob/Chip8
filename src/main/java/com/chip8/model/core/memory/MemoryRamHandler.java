package com.chip8.model.core.memory;

import com.chip8.api.core.memory.MemoryRam;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class MemoryRamHandler implements MemoryRam {

    private static final Integer MEMORY_SIZE = 4094;

    private final Byte[] memory = new Byte[MEMORY_SIZE];

    @Override
    public Byte read(final Integer position) {
        Assert.notNull(position, "The position can not be null");
        Assert.isTrue(position >= 0, "The position can not be negative");
        Assert.isTrue(position < MEMORY_SIZE, "The position can not be negative");
        return this.memory[position];
    }

    @Override
    public void write(final Integer position, final Byte data) {
        Assert.notNull(position, "The position can not be null");
        Assert.notNull(data, "The data can not be null");
        Assert.isTrue(position >= 0, "The position can not be negative");
        Assert.isTrue(position < MEMORY_SIZE, "The position can not be negative");
        this.memory[position] = data;
    }

}
