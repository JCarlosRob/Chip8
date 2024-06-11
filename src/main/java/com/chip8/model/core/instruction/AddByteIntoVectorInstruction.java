package com.chip8.model.core.instruction;

import com.chip8.api.core.register.VRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HexFormat;

@Component
public class AddByteIntoVectorInstruction extends InstructionAbstract {

    private static final String COMMAND_REGEX = "^7\\w{3}";

    private final VRegister vRegister;

    @Autowired
    public AddByteIntoVectorInstruction(final VRegister vRegister) {
        super(COMMAND_REGEX);
        this.vRegister = vRegister;
    }

    @Override
    public void execute(final String data) {
        final Integer vx = HexFormat.fromHexDigits(data.substring(1, 2));
        final Integer kk = HexFormat.fromHexDigits(data.substring(2, 4));
        this.vRegister.add(vx, kk);
    }
}