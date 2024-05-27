package com.chip8.model.core.registers;

import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class VRegister {

    private static final Integer MAX_LENGTH_V_REGISTER = 16;

    private final Byte[] v = new Byte[MAX_LENGTH_V_REGISTER];

    public void setVRegister(final Integer position, final Byte data) {
        Assert.notNull(position, "The position can not be null");
        Assert.isTrue(position >= 0, "The position can not be negative");
        Assert.isTrue(position < this.v.length, "The position can not be greater than " + (this.v.length - 1));
        this.v[position] = data;
    }

    public Byte getVRegister(final Integer position) {
        Assert.notNull(position, "The position can not be null");
        Assert.isTrue(position >= 0, "The position can not be negative");
        Assert.isTrue(position < this.v.length, "The position can not be greater than " + (this.v.length - 1));
        return this.v[position];
    }

}
