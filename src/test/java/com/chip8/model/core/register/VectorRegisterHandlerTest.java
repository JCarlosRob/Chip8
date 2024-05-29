package com.chip8.model.core.register;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class VectorRegisterHandlerTest {

    private VectorRegisterHandler vRegisterHandler;

    @BeforeEach
    void setUp() {
        this.vRegisterHandler = new VectorRegisterHandler();
    }

    @Test
    void getVRegister_test() {
        this.vRegisterHandler.setVRegister(0, 1);
        Assertions.assertEquals(1, this.vRegisterHandler.getVRegister(0));
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
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.vRegisterHandler.setVRegister(-1, 1));
    }

    @Test
    void setVRegister_inputGreaterThan15_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.vRegisterHandler.setVRegister(16, 1));
    }

}
