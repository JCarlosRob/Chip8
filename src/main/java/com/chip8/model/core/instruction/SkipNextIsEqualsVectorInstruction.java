package com.chip8.model.core.instruction;

import com.chip8.api.core.instruction.Instruction;
import com.chip8.api.core.register.VectorRegister;
import com.chip8.model.core.register.ProgramCounterHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.HexFormat;
import java.util.Objects;

@Component
public class SkipNextIsEqualsVectorInstruction implements Instruction {

    private static final Integer COMMAND = HexFormat.fromHexDigits("5");

    private final ProgramCounterHandler pc;

    private final VectorRegister vectorXRegister;

    private final VectorRegister vectorYRegister;

    @Autowired
    public SkipNextIsEqualsVectorInstruction(final ProgramCounterHandler pc, final VectorRegister vectorXRegister,
                                             final VectorRegister vectorYRegister) {
        this.pc = pc;
        this.vectorXRegister = vectorXRegister;
        this.vectorYRegister = vectorYRegister;
    }

    @Override
    public Boolean isExecutable(final String data) {
        Assert.notNull(data, "Data can not be null");
        Assert.hasLength(data, "Data can not empty");
        return Objects.equals(COMMAND, HexFormat.fromHexDigits(data.substring(0, 1)));
    }

    @Override
    public void run(final String data) {
        Assert.notNull(data, "Data can not be null");
        Assert.hasLength(data, "Data can not empty");

        final Integer vx = HexFormat.fromHexDigits(data.substring(1, 2));
        final Integer vy = HexFormat.fromHexDigits(data.substring(2, 3));

        if (Objects.equals(this.vectorXRegister.getVRegister(vx), this.vectorYRegister.getVRegister(vy))) {
            this.pc.next(2);
        }
    }
}