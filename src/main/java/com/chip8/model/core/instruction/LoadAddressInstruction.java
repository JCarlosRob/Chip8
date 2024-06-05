package com.chip8.model.core.instruction;

import com.chip8.api.core.register.IRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HexFormat;

@Component
public class LoadAddressInstruction extends InstructionAbstract {

    private static final String COMMAND_REGEX = "^A\\w{3}";

    private final IRegister iRegister;

    @Autowired
    public LoadAddressInstruction(final IRegister iRegister) {
        super(COMMAND_REGEX);
        this.iRegister = iRegister;
    }

    @Override
    public void execute(final String data) {
        final Integer address = HexFormat.fromHexDigits(data.substring(1));
        this.iRegister.setVI(address);
    }
}