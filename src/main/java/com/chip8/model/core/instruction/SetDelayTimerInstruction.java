package com.chip8.model.core.instruction;

import com.chip8.api.core.register.DelayTimerRegister;
import com.chip8.api.core.register.ProgramCounter;
import com.chip8.api.core.register.VRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HexFormat;

@Component
public class SetDelayTimerInstruction extends InstructionAbstract {

    private static final String COMMAND_REGEX = "^F\\w{1}15$";

    private final VRegister vRegister;

    private final DelayTimerRegister delayTimerRegister;

    @Autowired
    public SetDelayTimerInstruction(final ProgramCounter pc, final VRegister vRegister, final DelayTimerRegister delayTimerRegister) {
        super(COMMAND_REGEX);
        this.vRegister = vRegister;
        this.delayTimerRegister = delayTimerRegister;
    }

    @Override
    public void execute(final String opcode) {
        this.delayTimerRegister.set(this.vRegister.get(HexFormat.fromHexDigits(opcode.substring(1, 2))));
    }
}