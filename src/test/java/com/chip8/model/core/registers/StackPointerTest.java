package com.chip8.model.core.registers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class StackPointerTest {

    private final StackPointer stackPointer = new StackPointer();

    @Test
    void decrement_stackPointerIsZero_resultNoDecrement_test() {
        this.stackPointer.setSp((short) 0);
        this.stackPointer.decrement();
        Assertions.assertEquals((short) 0, this.stackPointer.getSp());
    }

    @Test
    void decrement_stackPointerGreaterThanZero_resultDecrement_test() {
        this.stackPointer.setSp((short) 1);
        this.stackPointer.decrement();
        Assertions.assertEquals((short) 0, this.stackPointer.getSp());
    }

    @Test
    void increase_stackPointerIsZero_resultNoDecrement_test() {
        this.stackPointer.setSp((short) 11);
        this.stackPointer.increase();
        Assertions.assertEquals((short) 11, this.stackPointer.getSp());
    }

    @Test
    void increase_stackPointerGreaterThanZero_resultDecrement_test() {
        this.stackPointer.setSp((short) 10);
        this.stackPointer.increase();
        Assertions.assertEquals((short) 11, this.stackPointer.getSp());
    }

}
