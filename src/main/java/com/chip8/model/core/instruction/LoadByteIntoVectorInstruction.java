package com.chip8.model.core.instruction;

import com.chip8.api.core.register.VectorRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HexFormat;

@Component
public class LoadByteIntoVectorInstruction extends InstructionAbstract {

    private static final String COMMAND_REGEX = "^6\\w{3}";

    private final VectorRegister vectorXRegister;

    @Autowired
    public LoadByteIntoVectorInstruction(final VectorRegister vectorXRegister) {
        super(COMMAND_REGEX);
        this.vectorXRegister = vectorXRegister;
    }

    @Override
    public void execute(final String data) {
        final Integer vx = HexFormat.fromHexDigits(data.substring(1, 2));
        final Integer kk = HexFormat.fromHexDigits(data.substring(2, 4));
        this.vectorXRegister.setVRegister(vx, kk);
    }
}