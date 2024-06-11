package com.chip8.model.core.instruction;

import com.chip8.api.core.memory.Memory;
import com.chip8.api.core.register.ProgramCounter;
import com.chip8.api.core.register.StackPointer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HexFormat;

@Component
public class CallAddressInstruction extends InstructionAbstract {

    private static final String COMMAND_REGEX = "^2\\w{3}";

    private final ProgramCounter pc;

    private final StackPointer sp;

    private final Memory memoryStack;

    @Autowired
    public CallAddressInstruction(final ProgramCounter pc, final StackPointer sp, final Memory memoryStack) {
        super(COMMAND_REGEX);
        this.pc = pc;
        this.sp = sp;
        this.memoryStack = memoryStack;
    }

    @Override
    public void execute(final String data) {
        this.sp.increase();
        this.memoryStack.write(this.sp.get(), this.pc.get());
        this.pc.set(HexFormat.fromHexDigits(data.substring(1)));
    }

}