package com.chip8.model.core.instruction;

import com.chip8.api.core.memory.Memory;
import com.chip8.api.core.register.ProgramCounter;
import com.chip8.api.core.register.StackPointer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReturnFromSubroutineInstruction extends InstructionAbstract {

    private static final String COMMAND_REGEX = "00EE";

    private final ProgramCounter pc;

    private final StackPointer sp;

    private final Memory memoryStack;

    @Autowired
    public ReturnFromSubroutineInstruction(final ProgramCounter pc, final StackPointer sp, final Memory memoryStack) {
        super(COMMAND_REGEX);
        this.pc = pc;
        this.sp = sp;
        this.memoryStack = memoryStack;
    }

    @Override
    public void execute(final String data) {
        this.sp.decrement();
        this.pc.set(this.memoryStack.read(this.sp.get()));
    }
}
