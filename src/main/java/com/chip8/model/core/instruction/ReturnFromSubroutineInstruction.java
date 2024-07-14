package com.chip8.model.core.instruction;

import com.chip8.api.core.memory.MemoryStack;
import com.chip8.api.core.register.ProgramCounter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReturnFromSubroutineInstruction extends InstructionAbstract {

    private static final String COMMAND_REGEX = "00EE";

    private final ProgramCounter pc;

    private final MemoryStack memoryStack;

    @Autowired
    public ReturnFromSubroutineInstruction(final ProgramCounter pc, final MemoryStack memoryStack) {
        super(COMMAND_REGEX);
        this.pc = pc;
        this.memoryStack = memoryStack;
    }

    @Override
    public void execute(final String data) {
        this.pc.set(this.memoryStack.pop());
    }
}
