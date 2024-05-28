package com.chip8.model.core.register;

import com.chip8.api.core.register.ProgramCounter;
import lombok.Getter;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
@Getter
public class ProgramCounterHandler implements ProgramCounter {

    private Short pc;

    @Override
    public void setPc(final Short address) {
        Assert.notNull(address, "The address can be null.");
        Assert.isTrue(address > -1, "The address can not be negative");
        Assert.isTrue(address < 4096, "The address can not be greater than 4095");
        this.pc = address;
    }

    @Override
    public void next() {
        this.pc = (short) (this.pc + 200);
    }

}
