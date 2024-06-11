package com.chip8.model.core.register;

import com.chip8.api.core.register.IndexRegister;
import org.springframework.stereotype.Component;

@Component
public class IndexRegisterHandler implements IndexRegister {

    private Integer vI;

    @Override
    public void set(final Integer data) {
        this.vI = data;
    }

    @Override
    public Integer get() {
        return this.vI;
    }
}
