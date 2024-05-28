package com.chip8.model.core.memory;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MemoryRamTest {

    private final MemoryRam memoryRam = new MemoryRam();

    @Test
    void read_inputMinPosition_test() {
        this.memoryRam.write(0, Byte.valueOf("2"));
        final Byte result = this.memoryRam.read(0);
        Assertions.assertEquals(Byte.valueOf("2"), result);
    }

    @Test
    void read_inputMaxPosition_test() {
        this.memoryRam.write(4093, Byte.valueOf("2"));
        final Byte result = this.memoryRam.read(4093);
        Assertions.assertEquals(Byte.valueOf("2"), result);
    }

    @Test
    void read_inputNegativePosition_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.memoryRam.read(-1));
    }

    @Test
    void read_inputPositionGreaterThan4093_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.memoryRam.read(4094));
    }

    @Test
    void read_inputPositionNull_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.memoryRam.read(null));
    }

    @Test
    void write_inputPositionNull_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.memoryRam.write(null, Byte.valueOf("1")));
    }

    @Test
    void write_inputPositionGreaterThan4093_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.memoryRam.write(4094, Byte.valueOf("1")));
    }

    @Test
    void write_inputNegativePosition_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.memoryRam.write(-1, Byte.valueOf("1")));
    }

    @Test
    void write_inputDataNull_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.memoryRam.write(1, null));
    }

    @Test
    void write_inputPositionAndDataNull_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.memoryRam.write(1, null));
    }

}
