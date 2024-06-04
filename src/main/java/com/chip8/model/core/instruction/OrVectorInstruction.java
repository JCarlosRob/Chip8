package com.chip8.model.core.instruction;

import com.chip8.api.core.register.VectorRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HexFormat;

@Component
public class OrVectorInstruction extends InstructionAbstract {

    private static final String COMMAND_REGEX = "^8\\w{2}1$";

    private final VectorRegister vectorXRegister;

    private final VectorRegister vectorYRegister;

    @Autowired
    public OrVectorInstruction(final VectorRegister vectorXRegister, final VectorRegister vectorYRegister) {
        super(COMMAND_REGEX);
        this.vectorXRegister = vectorXRegister;
        this.vectorYRegister = vectorYRegister;
    }

    @Override
    public void execute(final String data) {
        final Integer positionVx = HexFormat.fromHexDigits(data.substring(1, 2));
        final Integer vx = this.vectorXRegister.getVRegister(HexFormat.fromHexDigits(data.substring(1, 2)));
        final Integer vy = this.vectorYRegister.getVRegister(HexFormat.fromHexDigits(data.substring(2, 3)));
        this.vectorXRegister.setVRegister(positionVx, vx | vy);
    }
}