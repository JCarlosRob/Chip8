package com.chip8.model.core.register;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StackPointerHandlerTest {

    private StackPointerHandler stackPointerHandler;

    @BeforeEach
    void setUp() {
        this.stackPointerHandler = new StackPointerHandler();
    }

    @Test
    void decrement_stackPointerGreaterThanZero_resultDecrement_test() {
        this.stackPointerHandler.set(1);
        this.stackPointerHandler.decrement();
        Assertions.assertEquals((short) 0, this.stackPointerHandler.get());
    }

    @Test
    void decrement_stackPointerIsZero_resultException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.stackPointerHandler.decrement());
    }

    @Test
    void increase_stackPointerGreaterThanZero_resultDecrement_test() {
        this.stackPointerHandler.set(10);
        this.stackPointerHandler.increase();
        Assertions.assertEquals((short) 11, this.stackPointerHandler.get());
    }

    @Test
    void increase_stackPointerIsEleven_resultException_test() {
        this.stackPointerHandler.set(11);
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.stackPointerHandler.increase());
    }

    @Test
    void set_inputDataNull_resultException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.stackPointerHandler.set(null));
    }

}
