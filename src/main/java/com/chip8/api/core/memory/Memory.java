package com.chip8.api.core.memory;

public interface Memory {

    void write(Integer position, Integer data);

    void write(Integer position, Integer[] data);

    Integer read(Integer position);

    Integer[] read(Integer start, Integer end);

}
