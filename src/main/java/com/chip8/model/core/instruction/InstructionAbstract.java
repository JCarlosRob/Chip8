package com.chip8.model.core.instruction;

import com.chip8.api.core.instruction.Instruction;
import org.springframework.util.Assert;

import java.util.regex.Pattern;

public abstract class InstructionAbstract implements Instruction {

    private final String commandRegex;

    InstructionAbstract(final String commandRegex) {
        Assert.notNull(commandRegex, "Command regex can not be null");
        this.commandRegex = commandRegex;
    }

    @Override
    public Boolean isExecutable(final String data) {
        Assert.notNull(data, "Data can not be null");
        Assert.hasLength(data, "Data can not be empty");
        return Pattern.compile(this.commandRegex).matcher(data).find();
    }

    @Override
    public void run(final String data) {
        Assert.notNull(data, "Data can not be empty");
        Assert.hasLength(data, "Data can not be empty");
        this.execute(data);
    }

    public abstract void execute(final String data);

}
