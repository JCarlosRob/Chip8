package com.chip8.model.core.instruction;

import com.chip8.api.core.register.ProgramCounter;
import com.chip8.api.core.register.VRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HexFormat;

@Component
public class JumpAddressMoreVectorInstruction extends InstructionAbstract {

    private static final String COMMAND_REGEX = "^B\\w{3}";

    private final ProgramCounter pc;

    private final VRegister vRegister;

    @Autowired
    public JumpAddressMoreVectorInstruction(final ProgramCounter pc, final VRegister vRegister) {
        super(COMMAND_REGEX);
        this.pc = pc;
        this.vRegister = vRegister;
    }

    @Override
    public void execute(final String data) {
        this.pc.set(HexFormat.fromHexDigits(data.substring(1)) + this.vRegister.get(0));
    }
}