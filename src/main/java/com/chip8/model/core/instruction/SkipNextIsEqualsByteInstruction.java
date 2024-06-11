package com.chip8.model.core.instruction;

import com.chip8.api.core.register.ProgramCounter;
import com.chip8.api.core.register.VRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HexFormat;
import java.util.Objects;

@Component
public class SkipNextIsEqualsByteInstruction extends InstructionAbstract {

    private static final String COMMAND_REGEX = "^3\\w{3}";

    private final ProgramCounter pc;

    private final VRegister vRegister;

    @Autowired
    public SkipNextIsEqualsByteInstruction(final ProgramCounter pc, final VRegister vRegister) {
        super(COMMAND_REGEX);
        this.pc = pc;
        this.vRegister = vRegister;
    }

    @Override
    public void execute(final String data) {
        final Integer vx = HexFormat.fromHexDigits(data.substring(1, 2));
        final Integer kk = HexFormat.fromHexDigits(data.substring(2));

        if (Objects.equals(this.vRegister.get(vx), kk)) {
            this.pc.next(2);
        }
    }
}