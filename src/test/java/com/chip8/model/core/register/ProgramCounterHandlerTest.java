package com.chip8.model.core.register;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProgramCounterHandlerTest {

    private ProgramCounterHandler programCounterHandler;

    @BeforeEach
    void setUp() {
        this.programCounterHandler = new ProgramCounterHandler();
    }

    @Test
    void set_test() {
        this.programCounterHandler.set(0);
        Assertions.assertEquals(0, this.programCounterHandler.get());
    }

    @Test
    void set_inputNull_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.programCounterHandler.set(null));
    }

    @Test
    void set_inputNegative_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.programCounterHandler.set(-1));
    }

    @Test
    void set_inputGreaterThan4095_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.programCounterHandler.set(4096));
    }

    @Test
    void next_returnIncreaseBy1_test() {
        this.programCounterHandler.next();
        Assertions.assertEquals(514, this.programCounterHandler.get());
    }

    @Test
    void next_input2Times_returnIncreaseBy2_test() {
        this.programCounterHandler.next(2);
        Assertions.assertEquals(514, this.programCounterHandler.get());
    }

    @Test
    void next_inputNull_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.programCounterHandler.next(null));
    }

}
