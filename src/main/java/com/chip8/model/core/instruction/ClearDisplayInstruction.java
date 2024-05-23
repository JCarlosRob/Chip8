package com.chip8.model.core.instruction;

import com.chip8.api.core.buffer.Buffer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClearDisplayInstruction extends Instruction {

    @Autowired
    private Buffer displayBuffer;

    public ClearDisplayInstruction() {
        setBehavior(() -> displayBuffer.reset());
        setType(InstructionType.CLS);
    }

}
