package com.chip8.model.core.instruction;

import org.springframework.stereotype.Component;

@Component
public class SysCallInstruction extends Instruction {

    public SysCallInstruction() {
        setBehavior(() -> System.out.println("Hola, Mundo!"));
        setType(InstructionType.SYS_CALL);
    }

}
