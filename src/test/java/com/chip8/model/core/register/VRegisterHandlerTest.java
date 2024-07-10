package com.chip8.model.core.register;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.stream.IntStream;

@ExtendWith(SpringExtension.class)
@Import(VRegisterHandler.class)
class VRegisterHandlerTest {

    @Autowired
    private VRegisterHandler vRegisterHandler;

    @BeforeEach
    void setUp() {
        this.vRegisterHandler.reset();
    }

    @Test
    void get_test() {
        this.vRegisterHandler.set(0, 1);
        Assertions.assertEquals(1, this.vRegisterHandler.get(0));
    }

    @Test
    void get_inputNegativePosition_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.vRegisterHandler.get(-1));
    }

    @Test
    void get_inputGreaterThan15_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.vRegisterHandler.get(16));
    }

    @Test
    void set_inputNegativePosition_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.vRegisterHandler.set(-1, 1));
    }

    @Test
    void set_inputGreaterThan15_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.vRegisterHandler.set(16, 1));
    }

    @Test
    void add_test() {
        this.vRegisterHandler.set(0, 3);
        this.vRegisterHandler.add(0, 1);
        final Integer result = this.vRegisterHandler.get(0);
        Assertions.assertEquals(4, result);
    }

    @Test
    void reset_test() {
        this.vRegisterHandler.set(1, 1);
        this.vRegisterHandler.reset();
        IntStream.range(0, 16).forEach(value -> Assertions.assertEquals(0, this.vRegisterHandler.get(value)));
    }

}
