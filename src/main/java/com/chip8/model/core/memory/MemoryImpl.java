package com.chip8.model.core.memory;

import com.chip8.api.core.memory.Memory;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.stream.IntStream;

public class MemoryImpl implements Memory {
    private final Integer memorySize;

    private final Integer[] memory;

    public MemoryImpl(final Integer memorySize) {
        this.memorySize = memorySize;

        this.memory = IntStream.range(0, this.memorySize)
                .mapToObj(c -> 0)
                .toArray(Integer[]::new);
    }

    @Override
    public Integer read(final Integer position) {
        Assert.notNull(position, "The position can not be null");
        Assert.isTrue(position >= 0, "The position can not be negative");
        Assert.isTrue(position < this.memorySize, "The position can not greater than " + this.memorySize);
        return this.memory[position];
    }

    @Override
    public Integer[] read(final Integer start, final Integer end) {
        Assert.notNull(start, "The start position can not be null");
        Assert.notNull(end, "The end position can not be null");
        Assert.isTrue(end > 0, "The end position can not be less than 1");
        Assert.isTrue(start >= 0, "The start position can not be negative");
        Assert.isTrue(end > start, "The end position can not be less than start position");
        return Arrays.copyOfRange(this.memory, start, end + 1);
    }

    @Override
    public void write(final Integer position, final Integer data) {
        Assert.notNull(position, "The position can not be null");
        Assert.notNull(data, "The data can not be null");
        Assert.isTrue(position >= 0, "The position can not be negative");
        Assert.isTrue(position < this.memorySize, "The position can not greater than " + this.memorySize);
        this.memory[position] = data;
    }

    @Override
    public void write(final Integer position, final Integer[] data) {
        Assert.notNull(data, "The data can not be null");
        Assert.notNull(position, "The position can not be null");
        Assert.isTrue(position >= 0, "The position can not be negative");
        IntStream.range(position, data.length + position).forEach(i -> this.write(i, data[i - position]));
    }

}
