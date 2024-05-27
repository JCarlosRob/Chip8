package com.chip8.model.core.instruction;

import com.chip8.api.core.instruction.Instruction;
import com.chip8.model.core.registers.ProgramCounter;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.HexFormat;
import java.util.Objects;

@Component
public class SysCallInstruction implements Instruction {

    private static final Integer COMMAND = HexFormat.fromHexDigits("0");

    private final ProgramCounter pc;

    public SysCallInstruction(final ProgramCounter pc) {
        this.pc = pc;
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
        this.pc.setPc((short) HexFormat.fromHexDigits(data.substring(1)));
    }
}
