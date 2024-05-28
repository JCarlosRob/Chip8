package com.chip8.model.core.register;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class VRegisterHandlerTest {

    private VRegisterHandler vRegisterHandler;

    @BeforeEach
    void setUp() {
        this.vRegisterHandler = new VRegisterHandler();
    }

    @Test
    void getVRegister_test() {
        this.vRegisterHandler.setVRegister(0, Byte.valueOf("1"));
        Assertions.assertEquals(Byte.valueOf("1"), this.vRegisterHandler.getVRegister(0));
    }

    @Test
    void getVRegister_inputNegativePosition_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.vRegisterHandler.getVRegister(-1));
    }

    @Test
    void getVRegister_inputGreaterThan15_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.vRegisterHandler.getVRegister(16));
    }

    @Test
    void setVRegister_inputNegativePosition_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.vRegisterHandler.setVRegister(-1, Byte.valueOf("1")));
    }

    @Test
    void setVRegister_inputGreaterThan15_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.vRegisterHandler.setVRegister(16, Byte.valueOf("1")));
    }

}
