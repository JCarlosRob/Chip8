package com.chip8.model.core.register;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TimerRegisterTest {

    private TimerRegisterHandler timerRegister;

    @BeforeEach
    void setUp() {
        this.timerRegister = new TimerRegisterHandler();
    }

    @Test
    void set_inputNull_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.timerRegister.set(null));
    }

    @Test
    void set_returnValueInput_test() {
        this.timerRegister.set(1);
        Assertions.assertEquals(1, this.timerRegister.get());

    }

}
