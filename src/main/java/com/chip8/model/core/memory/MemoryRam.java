package com.chip8.model.core.memory;

import com.chip8.api.core.memory.Memory;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class MemoryRam implements Memory {

    private static final Integer MEMORY_SIZE = 4094;

    private final Integer[] memory = new Integer[MEMORY_SIZE];

    @Override
    public Integer read(final Integer position) {
        Assert.notNull(position, "The position can not be null");
        Assert.isTrue(position >= 0, "The position can not be negative");
        Assert.isTrue(position < MEMORY_SIZE, "The position can not greater than " + MEMORY_SIZE);
        return this.memory[position];
    }

    @Override
    public void write(final Integer position, final Integer data) {
        Assert.notNull(position, "The position can not be null");
        Assert.notNull(data, "The data can not be null");
        Assert.isTrue(position >= 0, "The position can not be negative");
        Assert.isTrue(position < MEMORY_SIZE, "The position can not greater than " + MEMORY_SIZE);
        this.memory[position] = data;
    }

}