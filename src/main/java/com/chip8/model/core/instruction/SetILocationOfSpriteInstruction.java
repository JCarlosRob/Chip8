package com.chip8.model.core.instruction;

import com.chip8.api.core.buffer.Buffer;
import com.chip8.api.core.register.IndexRegister;
import com.chip8.api.core.register.ProgramCounter;
import com.chip8.api.core.register.VRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HexFormat;

@Component
public class SetILocationOfSpriteInstruction extends InstructionAbstract {

    private static final String COMMAND_REGEX = "^F\\w{1}29$";

    private final VRegister vRegister;

    private final Buffer displayBuffer;

    private final IndexRegister indexRegister;

    @Autowired
    public SetILocationOfSpriteInstruction(final ProgramCounter pc, final VRegister vRegister, final Buffer displayBuffer,
                                           final IndexRegister indexRegister) {
        super(COMMAND_REGEX);
        this.vRegister = vRegister;
        this.displayBuffer = displayBuffer;
        this.indexRegister = indexRegister;
    }

    @Override
    public void execute(final String opcode) {
        final Integer vData = this.vRegister.get(HexFormat.fromHexDigits(opcode.substring(1, 2)));
        this.indexRegister.set(this.displayBuffer.read(vData));
    }
}