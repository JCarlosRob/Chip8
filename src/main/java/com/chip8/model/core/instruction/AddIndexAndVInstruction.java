package com.chip8.model.core.instruction;

import com.chip8.api.core.register.IndexRegister;
import com.chip8.api.core.register.VRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HexFormat;

@Component
public class AddIndexAndVInstruction extends InstructionAbstract {

    private static final String COMMAND_REGEX = "^F\\w{1}1E$";

    private final VRegister vRegister;

    private final IndexRegister indexRegister;

    @Autowired
    public AddIndexAndVInstruction(final VRegister vRegister, final IndexRegister indexRegister) {
        super(COMMAND_REGEX);
        this.vRegister = vRegister;
        this.indexRegister = indexRegister;

    }

    @Override
    public void execute(final String data) {
        this.indexRegister.set(this.indexRegister.get() + this.vRegister.get(HexFormat.fromHexDigits(data.substring(1, 2))));
    }
}