package com.chip8.model.core.registers;

import com.chip8.api.core.registers.Registers;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class RegistersHandler implements Registers {

    private static final Integer MAX_LENGTH_V_REGISTER = 16;

    private final Byte[] v = new Byte[MAX_LENGTH_V_REGISTER];

    private Short vI;

    @Override
    public void setVRegister(final Integer position, final Byte data) {
        Assert.notNull(position, "The position can not be null");
        Assert.isTrue(position >= 0, "The position can not be negative");
        Assert.isTrue(position < this.v.length, "The position can not be greater than " + (this.v.length - 1));
        this.v[position] = data;
    }

    @Override
    public Byte getVRegister(final Integer position) {
        Assert.notNull(position, "The position can not be null");
        Assert.isTrue(position >= 0, "The position can not be negative");
        Assert.isTrue(position < this.v.length, "The position can not be greater than " + (this.v.length - 1));
        return this.v[position];
    }

    @Override
    public void setIRegister(final Short data) {
        this.vI = data;
    }

    @Override
    public Short getIRegister() {
        return this.vI;
    }
}
