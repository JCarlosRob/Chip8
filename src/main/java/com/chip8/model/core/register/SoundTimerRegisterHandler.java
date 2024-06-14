package com.chip8.model.core.register;

import com.chip8.api.core.register.TimerRegister;
import org.springframework.stereotype.Component;

@Component("soundTimerRegister")
public class SoundTimerRegisterHandler implements TimerRegister {

    private Integer delayTimer;

    @Override
    public void set(final Integer data) {
        this.delayTimer = data;
    }

    @Override
    public Integer get() {
        return this.delayTimer;
    }
}
