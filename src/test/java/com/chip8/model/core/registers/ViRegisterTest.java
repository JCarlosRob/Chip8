package com.chip8.model.core.registers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ViRegisterTest {

    private final ViRegister viRegister = new ViRegister();

    @Test
    void getViRegister_test() {
        this.viRegister.setVI(Short.parseShort("1"));
        Assertions.assertEquals(Short.parseShort("1"), this.viRegister.getVI());
    }

}
