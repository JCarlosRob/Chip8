package com.chip8.model.core.register;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class IndexRegisterHandlerTest {

    private IndexRegisterHandler viRegisterHandler;

    @BeforeEach
    void setUp() {
        this.viRegisterHandler = new IndexRegisterHandler();
    }

    @Test
    void getRegister_test() {
        this.viRegisterHandler.set(1);
        Assertions.assertEquals(1, this.viRegisterHandler.get());
    }

}
