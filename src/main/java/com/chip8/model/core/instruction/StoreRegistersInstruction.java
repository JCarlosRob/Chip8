package com.chip8.model.core.instruction;

import com.chip8.api.core.memory.Memory;
import com.chip8.api.core.register.IndexRegister;
import com.chip8.api.core.register.VRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HexFormat;
import java.util.stream.IntStream;

@Component
public class StoreRegistersInstruction extends InstructionAbstract {

    private static final String COMMAND_REGEX = "^F\\w{1}55$";

    private final VRegister vRegister;

    private final IndexRegister indexRegister;

    private final Memory memoryRam;

    @Autowired
    public StoreRegistersInstruction(final VRegister vRegister, final IndexRegister indexRegister, final Memory memoryRam) {
        super(COMMAND_REGEX);
        this.vRegister = vRegister;
        this.indexRegister = indexRegister;
        this.memoryRam = memoryRam;
    }

    @Override
    public void execute(final String data) {
        IntStream.range(0, HexFormat.fromHexDigits(data.substring(1, 2)) + 1).forEach(i ->
                this.memoryRam.write(this.indexRegister.get() + i, this.vRegister.get(i)));
    }

}