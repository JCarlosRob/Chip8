package com.chip8.model.core.registers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RegistersHandlerTest {

    private RegistersHandler registersHandler;

    @BeforeEach
    void setUp() {
        this.registersHandler = new RegistersHandler();
    }

    @Test
    void getVRegister_test() {
        this.registersHandler.setVRegister(0, Byte.valueOf("1"));
        Assertions.assertEquals(Byte.valueOf("1"), this.registersHandler.getVRegister(0));
    }

    @Test
    void getVRegister_inputNegativePosition_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.registersHandler.getVRegister(-1));
    }

    @Test
    void getVRegister_inputGreaterThan15_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.registersHandler.getVRegister(16));
    }

    @Test
    void setVRegister_inputNegativePosition_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.registersHandler.setVRegister(-1, Byte.valueOf("1")));
    }

    @Test
    void setVRegister_inputGreaterThan15_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.registersHandler.setVRegister(16, Byte.valueOf("1")));
    }

    @Test
    void getIRegister_test() {
        this.registersHandler.setIRegister(Short.parseShort("1"));
        Assertions.assertEquals(Short.parseShort("1"), this.registersHandler.getIRegister());
    }

}
