package com.chip8.model.core.instruction;

import com.chip8.api.core.instruction.Instruction;
import com.chip8.model.core.registers.ProgramCounter;
import com.chip8.model.core.registers.StackPointer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.HexFormat;
import java.util.Objects;

@Component
public class ReturnFromSubroutineInstruction implements Instruction {

    private static final Integer COMMAND = HexFormat.fromHexDigits("00EE");

    private final ProgramCounter pc;

    private final StackPointer sp;

    @Autowired
    public ReturnFromSubroutineInstruction(final ProgramCounter pc, final StackPointer sp) {
        this.pc = pc;
        this.sp = sp;
    }

    @Override
    public Boolean isExecutable(final String data) {
        Assert.notNull(data, "Data can not be null");
        Assert.hasLength(data, "Data can not empty");
        return Objects.equals(COMMAND, HexFormat.fromHexDigits(data));
    }

    @Override
    public void run(final String data) {
        Assert.notNull(data, "Data can not be null");
        Assert.hasLength(data, "Data can not empty");
        this.sp.decrement();
        this.pc.setPc(this.sp.getSp());
    }
}
