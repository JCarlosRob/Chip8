package com.chip8.model.core.register;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ProgramCounterHandlerTest {

    private final ProgramCounterHandler programCounterHandler = new ProgramCounterHandler();

    @Test
    void setPc_test() {
        this.programCounterHandler.setPc((short) 0);
        Assertions.assertEquals((short) 0, this.programCounterHandler.getPc());
    }

    @Test
    void setPc_inputNull_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.programCounterHandler.setPc(null));
    }

    @Test
    void setPc_inputNegative_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.programCounterHandler.setPc((short) -1));
    }

    @Test
    void setPc_inputGreaterThan4095_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.programCounterHandler.setPc((short) 4096));
    }

}
