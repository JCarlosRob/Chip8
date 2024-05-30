package com.chip8.model.core.instruction;

import com.chip8.api.core.instruction.Instruction;
import com.chip8.api.core.register.VectorRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.HexFormat;
import java.util.Objects;

@Component
public class AddByteIntoVectorInstruction implements Instruction {

    private static final Integer COMMAND = HexFormat.fromHexDigits("7");

    private final VectorRegister vectorXRegister;

    @Autowired
    public AddByteIntoVectorInstruction(final VectorRegister vectorXRegister) {
        this.vectorXRegister = vectorXRegister;
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
        final Integer kk = HexFormat.fromHexDigits(data.substring(2, 4));
        this.vectorXRegister.add(vx, kk);
    }
}