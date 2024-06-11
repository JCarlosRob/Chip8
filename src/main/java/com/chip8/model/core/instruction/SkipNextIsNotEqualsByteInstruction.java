package com.chip8.model.core.instruction;

import com.chip8.api.core.register.VRegister;
import com.chip8.model.core.register.ProgramCounterHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HexFormat;
import java.util.Objects;

@Component
public class SkipNextIsNotEqualsByteInstruction extends InstructionAbstract {

    private static final String COMMAND_REGEX = "^4\\w{3}";

    private final ProgramCounterHandler pc;

    private final VRegister vectorXRegister;

    @Autowired
    public SkipNextIsNotEqualsByteInstruction(final ProgramCounterHandler pc, final VRegister vectorXRegister) {
        super(COMMAND_REGEX);
        this.pc = pc;
        this.vectorXRegister = vectorXRegister;
    }

    @Override
    public void execute(final String data) {
        final Integer vx = HexFormat.fromHexDigits(data.substring(1, 2));
        final Integer kk = HexFormat.fromHexDigits(data.substring(2));

        if (!Objects.equals(this.vectorXRegister.get(vx), kk)) {
            this.pc.next(2);
        }
    }
}