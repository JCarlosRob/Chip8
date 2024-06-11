package com.chip8.model.core.instruction;

import com.chip8.api.core.register.VRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HexFormat;

@Component
public class AddVectorIntoVectorWithCarryInstruction extends InstructionAbstract {

    private static final String COMMAND_REGEX = "^8\\w{2}4$";

    private final VRegister vRegister;

    @Autowired
    public AddVectorIntoVectorWithCarryInstruction(final VRegister vRegister) {
        super(COMMAND_REGEX);
        this.vRegister = vRegister;

    }

    @Override
    public void execute(final String data) {
        final Integer positionVx = HexFormat.fromHexDigits(data.substring(1, 2));
        final Integer vx = this.vRegister.get(HexFormat.fromHexDigits(data.substring(1, 2)));
        final Integer vy = this.vRegister.get(HexFormat.fromHexDigits(data.substring(2, 3)));

        final Integer vResult = vx + vy;
        this.vRegister.set(positionVx, vResult & 255);
        this.vRegister.set(15, vResult > 255 ? 1 : 0);
    }
}