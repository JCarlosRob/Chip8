package com.chip8.model.core.instruction;

import com.chip8.api.core.buffer.Buffer;
import com.chip8.api.core.instruction.Instruction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.HexFormat;
import java.util.Objects;

@Component
public class ClearDisplayInstruction implements Instruction {

    private static final Integer COMMAND = HexFormat.fromHexDigits("00E0");

    private final Buffer displayBuffer;

    @Autowired
    public ClearDisplayInstruction(final Buffer displayBuffer) {
        this.displayBuffer = displayBuffer;
    }

    @Override
    public Boolean isExecutable(final String data) {
        return StringUtils.hasLength(data) && Objects.equals(COMMAND, HexFormat.fromHexDigits(data));
    }

    @Override
    public void run() {
        this.displayBuffer.reset();
    }
}
