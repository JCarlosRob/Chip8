package com.chip8.api.core.register;

public interface ProgramCounter {

    Integer getPc();

    void setPc(Integer address);

    void next();

    void next(Integer times);
}
