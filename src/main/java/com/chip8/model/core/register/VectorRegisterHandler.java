package com.chip8.model.core.register;

import com.chip8.api.core.register.VectorRegister;
import org.springframework.util.Assert;

public class VectorRegisterHandler implements VectorRegister {

    private static final Integer MAX_LENGTH_V_REGISTER = 16;

    private final Integer[] v = new Integer[MAX_LENGTH_V_REGISTER];

    @Override
    public void setVRegister(final Integer position, final Integer data) {
        Assert.notNull(position, "The position can not be null");
        Assert.isTrue(position >= 0, "The position can not be negative");
        Assert.isTrue(position < this.v.length, "The position can not be greater than " + (this.v.length - 1));
        this.v[position] = data;
    }

    @Override
    public Integer getVRegister(final Integer position) {
        Assert.notNull(position, "The position can not be null");
        Assert.isTrue(position >= 0, "The position can not be negative");
        Assert.isTrue(position < this.v.length, "The position can not be greater than " + (this.v.length - 1));
        return this.v[position];
    }

}
