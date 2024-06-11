package com.chip8.api.core.register;

public interface ProgramCounter {

    Integer get();

    void set(Integer address);

    void next();

    void next(Integer times);
}
