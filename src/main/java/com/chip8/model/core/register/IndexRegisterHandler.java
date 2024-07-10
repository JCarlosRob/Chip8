package com.chip8.model.core.register;

import com.chip8.api.core.register.IndexRegister;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class IndexRegisterHandler implements IndexRegister {

    private Integer vI;

    @Override
    public void set(final Integer data) {
        Assert.notNull(data, "The data can not be null");
        this.vI = data;
    }

    @Override
    public Integer get() {
        return this.vI;
    }
}
