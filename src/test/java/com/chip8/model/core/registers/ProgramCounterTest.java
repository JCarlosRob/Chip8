package com.chip8.model.core.registers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ProgramCounterTest {

    private final ProgramCounter programCounter = new ProgramCounter();

    @Test
    void setPc_test() {
        this.programCounter.setPc((short) 0);
        Assertions.assertEquals((short) 0, this.programCounter.getPc());
    }

    @Test
    void setPc_inputNull_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.programCounter.setPc(null));
    }

    @Test
    void setPc_inputNegative_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.programCounter.setPc((short) -1));
    }

    @Test
    void setPc_inputGreaterThan4095_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.programCounter.setPc((short) 4096));
    }

}
