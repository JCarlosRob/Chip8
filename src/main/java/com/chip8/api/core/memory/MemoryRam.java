package com.chip8.api.core.memory;

public interface MemoryRam {

    void write(Integer position, Byte data);

    Byte read(Integer position);

}
