package com.chip8.model.core.memory;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MemoryStackTest {

    private final MemoryStack memoryStack = new MemoryStack();

    @Test
    void read_inputMinPosition_test() {
        this.memoryStack.write(0, Byte.valueOf("2"));
        final Byte result = this.memoryStack.read(0);
        Assertions.assertEquals(Byte.valueOf("2"), result);
    }

    @Test
    void read_inputMaxPosition_test() {
        this.memoryStack.write(19, Byte.valueOf("2"));
        final Byte result = this.memoryStack.read(19);
        Assertions.assertEquals(Byte.valueOf("2"), result);
    }

    @Test
    void read_inputNegativePosition_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.memoryStack.read(-1));
    }

    @Test
    void read_inputPositionGreaterThan4093_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.memoryStack.read(20));
    }

    @Test
    void read_inputPositionNull_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.memoryStack.read(null));
    }

    @Test
    void write_inputPositionNull_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.memoryStack.write(null, Byte.valueOf("1")));
    }

    @Test
    void write_inputPositionGreaterThan4093_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.memoryStack.write(4094, Byte.valueOf("1")));
    }

    @Test
    void write_inputNegativePosition_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.memoryStack.write(-1, Byte.valueOf("1")));
    }

    @Test
    void write_inputDataNull_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.memoryStack.write(1, null));
    }

    @Test
    void write_inputPositionAndDataNull_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.memoryStack.write(1, null));
    }

}
