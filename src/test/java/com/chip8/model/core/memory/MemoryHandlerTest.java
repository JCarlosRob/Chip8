package com.chip8.model.core.memory;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MemoryHandlerTest {

    private final MemoryHandler memoryHandler = new MemoryHandler();

    @Test
    public void read_inputMinPosition_test() {
        memoryHandler.write(0, Byte.valueOf("2"));
        final Byte result = memoryHandler.read(0);
        Assertions.assertEquals(Byte.valueOf("2"), result);
    }

    @Test
    public void read_inputMaxPosition_test() {
        memoryHandler.write(4093, Byte.valueOf("2"));
        final Byte result = memoryHandler.read(4093);
        Assertions.assertEquals(Byte.valueOf("2"), result);
    }

    @Test
    public void read_inputNegativePosition_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> memoryHandler.read(-1));
    }

    @Test
    public void read_inputPositionGreaterThan4093_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> memoryHandler.read(4094));
    }

    @Test
    public void read_inputPositionNull_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> memoryHandler.read(null));
    }

    @Test
    public void write_inputPositionNull_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> memoryHandler.write(null, Byte.valueOf("1")));
    }

    @Test
    public void write_inputPositionGreaterThan4093_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> memoryHandler.write(4094, Byte.valueOf("1")));
    }

    @Test
    public void write_inputNegativePosition_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> memoryHandler.write(-1, Byte.valueOf("1")));
    }

    @Test
    public void write_inputDataNull_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> memoryHandler.write(1, null));
    }

    @Test
    public void write_inputPositionAndDataNull_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> memoryHandler.write(1, null));
    }

}
