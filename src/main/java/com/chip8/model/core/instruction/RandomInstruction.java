package com.chip8.model.core.instruction;

import com.chip8.api.core.register.VRegister;
import org.springframework.stereotype.Component;

import java.util.HexFormat;
import java.util.Random;

@Component
public class RandomInstruction extends InstructionAbstract {

    private static final String COMMAND_REGEX = "^C\\w{3}";

    private final VRegister vectorXRegister;

    private final Random random;

    public RandomInstruction(final VRegister vectorXRegister, final Random random) {
        super(COMMAND_REGEX);
        this.vectorXRegister = vectorXRegister;
        this.random = random;
    }

    @Override
    public void execute(final String data) {
        final Integer randomResult = HexFormat.fromHexDigits(data.substring(2)) & (this.random.nextInt() & 255);
        this.vectorXRegister.set(HexFormat.fromHexDigits(data.substring(1, 2)), randomResult);
    }
}
