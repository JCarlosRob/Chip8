package com.chip8.model.core.instruction;

import com.chip8.api.core.register.IndexRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HexFormat;

@Component
public class LoadAddressInstruction extends InstructionAbstract {

    private static final String COMMAND_REGEX = "^A\\w{3}";

    private final IndexRegister indexRegister;

    @Autowired
    public LoadAddressInstruction(final IndexRegister indexRegister) {
        super(COMMAND_REGEX);
        this.indexRegister = indexRegister;
    }

    @Override
    public void execute(final String data) {
        final Integer address = HexFormat.fromHexDigits(data.substring(1));
        this.indexRegister.set(address);
    }
}