package com.chip8.model.core.memory;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class MemoryImplTest {

    private MemoryImpl memoryRam;

    @BeforeEach
    void setUp() {
        this.memoryRam = new MemoryImpl(4094);
    }

    @Test
    void read_inputMinPosition_test() {
        this.memoryRam.write(0, 2);
        final Integer result = this.memoryRam.read(0);
        Assertions.assertEquals(2, result);
    }

    @Test
    void read_inputMaxPosition_test() {
        this.memoryRam.write(4093, 2);
        final Integer result = this.memoryRam.read(4093);
        Assertions.assertEquals(2, result);
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
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.memoryRam.write(null, 1));
    }

    @Test
    void write_inputPositionGreaterThan4093_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.memoryRam.write(4094, 1));
    }

    @Test
    void write_inputNegativePosition_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.memoryRam.write(-1, 1));
    }

    @Test
    void write_inputDataNull_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.memoryRam.write(1, (Integer) null));
    }

    @Test
    void write_inputPositionAndDataNull_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.memoryRam.write(1, (Integer) null));
    }

    @Test
    void writeArrayData_inputData_test() {
        this.memoryRam.write(1, new Integer[]{2, 3});
        Assertions.assertEquals(2, this.memoryRam.read(1));
        Assertions.assertEquals(3, this.memoryRam.read(2));

    }

    @Test
    void writeArrayData_inputNullData_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.memoryRam.write(1, (Integer[]) null));
    }

    @Test
    void writeArrayData_inputNullPosition_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.memoryRam.write(null, new Integer[]{2, 3}));
    }

    @Test
    void writeArrayData_inputNegativePosition_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.memoryRam.write(-1, new Integer[]{2, 3}));
    }

    @Test
    void read_inputStartPositionNull_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.memoryRam.read(null, 0));
    }

    @Test
    void read_inputEndPositionNull_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.memoryRam.read(0, null));
    }

    @Test
    void read_inputEndPositionLessThanStartPosition_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.memoryRam.read(1, 0));
    }

    @Test
    void read_inputStartPositionNegative_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.memoryRam.read(-1, 1));
    }

    @Test
    void read_inputStartPositionIsGreaterThanEndPosition_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.memoryRam.read(2, 1));
    }

    @Test
    void read_inputEndPositionEqualsZero_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.memoryRam.read(0, 0));
    }

    @Test
    void read_returnSegmentOfData_test() {
        this.memoryRam.write(0, 1);
        this.memoryRam.write(1, 2);
        this.memoryRam.write(2, 3);
        this.memoryRam.write(3, 4);
        this.memoryRam.write(4, 5);
        final Integer[] result = this.memoryRam.read(1, 3);
        Assertions.assertEquals(0, Arrays.compare(new Integer[]{2, 3, 4}, result));
    }

}
