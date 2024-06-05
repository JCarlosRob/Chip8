package com.chip8.model.core.instruction;

import com.chip8.api.core.register.VectorRegister;
import com.chip8.model.core.register.ProgramCounterHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HexFormat;
import java.util.Objects;

@Component
public class SkipNextIsNotEqualsVectorInstruction extends InstructionAbstract {

    private static final String COMMAND_REGEX = "^9\\w{2}0$";

    private final ProgramCounterHandler pc;

    private final VectorRegister vectorXRegister;

    private final VectorRegister vectorYRegister;

    @Autowired
    public SkipNextIsNotEqualsVectorInstruction(final ProgramCounterHandler pc, final VectorRegister vectorXRegister, final VectorRegister vectorYRegister) {
        super(COMMAND_REGEX);
        this.pc = pc;
        this.vectorXRegister = vectorXRegister;
        this.vectorYRegister = vectorYRegister;
    }

    @Override
    public void execute(final String data) {
        final Integer vx = HexFormat.fromHexDigits(data.substring(1, 2));
        final Integer vy = HexFormat.fromHexDigits(data.substring(2, 3));

        if (!Objects.equals(this.vectorXRegister.getVRegister(vx), this.vectorYRegister.getVRegister(vy))) {
            this.pc.next(2);
        }
    }
}