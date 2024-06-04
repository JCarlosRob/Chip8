package com.chip8.model.core.instruction;

import com.chip8.model.core.register.ProgramCounterHandler;
import org.springframework.stereotype.Component;

import java.util.HexFormat;

@Component
public class SysCallInstruction extends InstructionAbstract {

    private static final String COMMAND_REGEX = "^0\\w{3}";

    private final ProgramCounterHandler pc;

    public SysCallInstruction(final ProgramCounterHandler pc) {
        super(COMMAND_REGEX);
        this.pc = pc;
    }

    @Override
    public void execute(final String data) {
        this.pc.setPc(HexFormat.fromHexDigits(data.substring(1)));
    }
}
