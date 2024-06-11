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
    void get_test() {
        this.vRegisterHandler.set(0, 1);
        Assertions.assertEquals(1, this.vRegisterHandler.get(0));
    }

    @Test
    void get_inputNegativePosition_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.vRegisterHandler.get(-1));
    }

    @Test
    void get_inputGreaterThan15_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.vRegisterHandler.get(16));
    }

    @Test
    void set_inputNegativePosition_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.vRegisterHandler.set(-1, 1));
    }

    @Test
    void set_inputGreaterThan15_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.vRegisterHandler.set(16, 1));
    }

    @Test
    void add_test() {
        this.vRegisterHandler.set(0, 3);
        this.vRegisterHandler.add(0, 1);
        final Integer result = this.vRegisterHandler.get(0);
        Assertions.assertEquals(4, result);
    }

}
