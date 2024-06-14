package com.chip8.model.core.instruction;

import com.chip8.api.core.register.IndexRegister;
import com.chip8.api.core.register.VRegister;
import com.chip8.model.core.memory.MemoryRam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HexFormat;
import java.util.stream.IntStream;

@Component
public class LoadMemoryToRegistersInstruction extends InstructionAbstract {

    private static final String COMMAND_REGEX = "^F\\w{1}65$";

    private final VRegister vRegister;

    private final IndexRegister indexRegister;

    private final MemoryRam memoryRam;

    @Autowired
    public LoadMemoryToRegistersInstruction(final VRegister vRegister, final IndexRegister indexRegister, final MemoryRam memoryRam) {
        super(COMMAND_REGEX);
        this.vRegister = vRegister;
        this.indexRegister = indexRegister;
        this.memoryRam = memoryRam;
    }

    @Override
    public void execute(final String data) {
        IntStream.range(0, HexFormat.fromHexDigits(data.substring(1, 2)) + 1).forEach(i ->
                this.vRegister.set(i, this.memoryRam.read(this.indexRegister.get() + i)));
    }

}