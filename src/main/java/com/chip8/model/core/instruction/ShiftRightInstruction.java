package com.chip8.model.core.instruction;

import com.chip8.api.core.register.VectorRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HexFormat;

@Component
public class ShiftRightInstruction extends InstructionAbstract {

    private static final String COMMAND_REGEX = "^8\\w{2}6$";

    private final VectorRegister vectorXRegister;

    @Autowired
    public ShiftRightInstruction(final VectorRegister vectorXRegister) {
        super(COMMAND_REGEX);
        this.vectorXRegister = vectorXRegister;
    }

    @Override
    public void execute(final String data) {
        final Integer positionVx = HexFormat.fromHexDigits(data.substring(1, 2));
        final Integer vx = this.vectorXRegister.getVRegister(HexFormat.fromHexDigits(data.substring(1, 2)));

        this.vectorXRegister.setVRegister(positionVx, vx / 2);
        this.vectorXRegister.setVRegister(15, vx & 1);
    }
}