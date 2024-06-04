package com.chip8.model.core.instruction;

import com.chip8.api.core.memory.Memory;
import com.chip8.model.core.register.ProgramCounterHandler;
import com.chip8.model.core.register.StackPointerHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HexFormat;

@Component
public class CallAddressInstruction extends InstructionAbstract {

    private static final String COMMAND_REGEX = "^2\\w{3}";

    private final ProgramCounterHandler pc;

    private final StackPointerHandler sp;

    private final Memory memoryStack;

    @Autowired
    public CallAddressInstruction(final ProgramCounterHandler pc, final StackPointerHandler sp, final Memory memoryStack) {
        super(COMMAND_REGEX);
        this.pc = pc;
        this.sp = sp;
        this.memoryStack = memoryStack;
    }

    @Override
    public void execute(final String data) {
        this.sp.increase();
        this.memoryStack.write(this.sp.getSp(), this.pc.getPc());
        this.pc.setPc(HexFormat.fromHexDigits(data.substring(1)));
    }

}