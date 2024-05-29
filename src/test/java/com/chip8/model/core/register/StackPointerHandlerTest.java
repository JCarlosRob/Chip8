package com.chip8.model.core.register;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class StackPointerHandlerTest {

    private final StackPointerHandler stackPointerHandler = new StackPointerHandler();

    @Test
    void decrement_stackPointerIsZero_resultNoDecrement_test() {
        this.stackPointerHandler.setSp(0);
        this.stackPointerHandler.decrement();
        Assertions.assertEquals((short) 0, this.stackPointerHandler.getSp());
    }

    @Test
    void decrement_stackPointerGreaterThanZero_resultDecrement_test() {
        this.stackPointerHandler.setSp(1);
        this.stackPointerHandler.decrement();
        Assertions.assertEquals((short) 0, this.stackPointerHandler.getSp());
    }

    @Test
    void increase_stackPointerIsZero_resultNoDecrement_test() {
        this.stackPointerHandler.setSp(11);
        this.stackPointerHandler.increase();
        Assertions.assertEquals((short) 11, this.stackPointerHandler.getSp());
    }

    @Test
    void increase_stackPointerGreaterThanZero_resultDecrement_test() {
        this.stackPointerHandler.setSp(10);
        this.stackPointerHandler.increase();
        Assertions.assertEquals((short) 11, this.stackPointerHandler.getSp());
    }

}
