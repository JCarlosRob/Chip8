package com.chip8.api.core.register;

public interface ProgramCounter {

    Short getPc();

    void setPc(Short address);

    void next();
}
