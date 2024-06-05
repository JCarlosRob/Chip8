package com.chip8.model.core.instruction;

import com.chip8.api.core.register.VectorRegister;
import com.chip8.model.core.register.ProgramCounterHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HexFormat;

@Component
public class JumpAddressMoreVectorInstruction extends InstructionAbstract {

    private static final String COMMAND_REGEX = "^B\\w{3}";

    private final ProgramCounterHandler pc;

    private final VectorRegister vectorXRegister;

    @Autowired
    public JumpAddressMoreVectorInstruction(final ProgramCounterHandler pc, final VectorRegister vectorXRegister) {
        super(COMMAND_REGEX);
        this.pc = pc;
        this.vectorXRegister = vectorXRegister;
    }

    @Override
    public void execute(final String data) {
        this.pc.setPc(HexFormat.fromHexDigits(data.substring(1)) + this.vectorXRegister.getVRegister(0));
    }
}