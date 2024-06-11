package com.chip8.model.core.register;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class IndexRegisterHandlerTest {

    private final IndexRegisterHandler viRegisterHandler = new IndexRegisterHandler();

    @Test
    void getRegister_test() {
        this.viRegisterHandler.set(1);
        Assertions.assertEquals(1, this.viRegisterHandler.get());
    }

}
