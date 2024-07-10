package com.chip8.model.core.register;

import com.chip8.api.core.register.TimerRegister;
import org.springframework.util.Assert;

public class TimerRegisterHandler implements TimerRegister {

    private Integer delayTimer = 0;

    @Override
    public void set(final Integer data) {
        Assert.notNull(data, "The data can not be null");
        this.delayTimer = data;
    }

    @Override
    public Integer get() {
        return this.delayTimer;
    }
}
