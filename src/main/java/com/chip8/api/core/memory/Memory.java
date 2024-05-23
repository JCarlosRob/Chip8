package com.chip8.api.core.memory;

public interface Memory {

    void write(Integer position, Byte data);

    Byte read(Integer position);

}
