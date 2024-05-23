package com.chip8.model.core.instruction;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Instruction {

    private InstructionType type;

    private Runnable behavior;

}
