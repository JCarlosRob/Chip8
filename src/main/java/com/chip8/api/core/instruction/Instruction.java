package com.chip8.api.core.instruction;

public interface Instruction {

    Boolean isExecutable(final String data);

    void run();

}
