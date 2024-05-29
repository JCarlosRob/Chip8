package com.chip8.model.core.register;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ProgramCounterHandlerTest {

    private final ProgramCounterHandler programCounterHandler = new ProgramCounterHandler();

    @Test
    void setPc_test() {
        this.programCounterHandler.setPc(0);
        Assertions.assertEquals(0, this.programCounterHandler.getPc());
    }

    @Test
    void setPc_inputNull_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.programCounterHandler.setPc(null));
    }

    @Test
    void setPc_inputNegative_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.programCounterHandler.setPc(-1));
    }

    @Test
    void setPc_inputGreaterThan4095_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.programCounterHandler.setPc(4096));
    }

    @Test
    void next_returnIncreaseBy1_test() {
        this.programCounterHandler.next();
        Assertions.assertEquals(1, this.programCounterHandler.getPc());
    }

    @Test
    void next_input2Times_returnIncreaseBy2_test() {
        this.programCounterHandler.next(2);
        Assertions.assertEquals(2, this.programCounterHandler.getPc());
    }

    @Test
    void next_inputNull_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.programCounterHandler.next(null));
    }

}
