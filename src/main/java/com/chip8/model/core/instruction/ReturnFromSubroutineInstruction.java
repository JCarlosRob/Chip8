package com.chip8.model.core.instruction;

import com.chip8.api.core.memory.Memory;
import com.chip8.model.core.register.ProgramCounterHandler;
import com.chip8.model.core.register.StackPointerHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReturnFromSubroutineInstruction extends InstructionAbstract {

    private static final String COMMAND_REGEX = "00EE";

    private final ProgramCounterHandler pc;

    private final StackPointerHandler sp;

    private final Memory memoryStack;

    @Autowired
    public ReturnFromSubroutineInstruction(final ProgramCounterHandler pc, final StackPointerHandler sp, final Memory memoryStack) {
        super(COMMAND_REGEX);
        this.pc = pc;
        this.sp = sp;
        this.memoryStack = memoryStack;
    }

    @Override
    public void execute(final String data) {
        this.sp.decrement();
        this.pc.set(this.memoryStack.read(this.sp.getSp()));
    }
}
