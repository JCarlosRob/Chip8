package com.chip8.model.core.instruction;

import com.chip8.api.core.register.VRegister;
import com.chip8.model.core.register.ProgramCounterHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HexFormat;

@Component
public class JumpAddressMoreVectorInstruction extends InstructionAbstract {

    private static final String COMMAND_REGEX = "^B\\w{3}";

    private final ProgramCounterHandler pc;

    private final VRegister vectorXRegister;

    @Autowired
    public JumpAddressMoreVectorInstruction(final ProgramCounterHandler pc, final VRegister vectorXRegister) {
        super(COMMAND_REGEX);
        this.pc = pc;
        this.vectorXRegister = vectorXRegister;
    }

    @Override
    public void execute(final String data) {
        this.pc.set(HexFormat.fromHexDigits(data.substring(1)) + this.vectorXRegister.get(0));
    }
}