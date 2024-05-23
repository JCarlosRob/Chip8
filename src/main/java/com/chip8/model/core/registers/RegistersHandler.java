package com.chip8.model.core.registers;

import com.chip8.api.core.registers.Registers;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
@Data
public class RegistersHandler implements Registers {

    private Byte[] v = new Byte[16];

    private Short vI;

    @Override
    public void setVRegister(Integer position, Byte data) {
        Assert.notNull(position, "The position can not be null");
        Assert.isTrue(position >= 0, "The position can not be negative");
        Assert.isTrue(position < v.length, "The position can not be greater than " + (v.length - 1));
        v[position] = data;
    }

    @Override
    public Byte getVRegister(Integer position) {
        Assert.notNull(position, "The position can not be null");
        Assert.isTrue(position >= 0, "The position can not be negative");
        Assert.isTrue(position < v.length, "The position can not be greater than " + (v.length - 1));
        return v[position];
    }

    @Override
    public void setIRegister(Short data) {
        vI = data;
    }

    @Override
    public Short getIRegister() {
        return vI;
    }
}
