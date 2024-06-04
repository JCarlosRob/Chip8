package com.chip8.model.core.instruction;

import com.chip8.api.core.buffer.Buffer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClearDisplayInstruction extends InstructionAbstract {

    private static final String COMMAND_REGEX = "00E0";

    private final Buffer displayBuffer;

    @Autowired
    public ClearDisplayInstruction(final Buffer displayBuffer) {
        super(COMMAND_REGEX);
        this.displayBuffer = displayBuffer;
    }

    @Override
    public void execute(final String data) {
        this.displayBuffer.reset();
    }
}
