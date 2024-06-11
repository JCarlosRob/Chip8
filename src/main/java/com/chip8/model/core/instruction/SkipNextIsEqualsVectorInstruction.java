package com.chip8.model.core.instruction;

import com.chip8.api.core.register.VRegister;
import com.chip8.model.core.register.ProgramCounterHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HexFormat;
import java.util.Objects;

@Component
public class SkipNextIsEqualsVectorInstruction extends InstructionAbstract {

    private static final String COMMAND_REGEX = "^5\\w{3}";

    private final ProgramCounterHandler pc;

    private final VRegister vectorXRegister;

    private final VRegister vectorYRegister;

    @Autowired
    public SkipNextIsEqualsVectorInstruction(final ProgramCounterHandler pc, final VRegister vectorXRegister,
                                             final VRegister vectorYRegister) {
        super(COMMAND_REGEX);
        this.pc = pc;
        this.vectorXRegister = vectorXRegister;
        this.vectorYRegister = vectorYRegister;
    }

    @Override
    public void execute(final String data) {
        final Integer vx = HexFormat.fromHexDigits(data.substring(1, 2));
        final Integer vy = HexFormat.fromHexDigits(data.substring(2, 3));

        if (Objects.equals(this.vectorXRegister.get(vx), this.vectorYRegister.get(vy))) {
            this.pc.next(2);
        }
    }
}