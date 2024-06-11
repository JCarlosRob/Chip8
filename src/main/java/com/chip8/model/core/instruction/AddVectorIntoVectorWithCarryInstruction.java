package com.chip8.model.core.instruction;

import com.chip8.api.core.register.VRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HexFormat;

@Component
public class AddVectorIntoVectorWithCarryInstruction extends InstructionAbstract {

    private static final String COMMAND_REGEX = "^8\\w{2}4$";

    private final VRegister vectorXRegister;

    private final VRegister vectorYRegister;

    @Autowired
    public AddVectorIntoVectorWithCarryInstruction(final VRegister vectorXRegister, final VRegister vectorYRegister) {
        super(COMMAND_REGEX);
        this.vectorXRegister = vectorXRegister;
        this.vectorYRegister = vectorYRegister;
    }

    @Override
    public void execute(final String data) {
        final Integer positionVx = HexFormat.fromHexDigits(data.substring(1, 2));
        final Integer vx = this.vectorXRegister.get(HexFormat.fromHexDigits(data.substring(1, 2)));
        final Integer vy = this.vectorYRegister.get(HexFormat.fromHexDigits(data.substring(2, 3)));

        final Integer vResult = vx + vy;
        this.vectorXRegister.set(positionVx, vResult & 255);
        this.vectorXRegister.set(15, vResult > 255 ? 1 : 0);
    }
}