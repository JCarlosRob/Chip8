package com.chip8.model.core.instruction;

import com.chip8.api.core.register.VRegister;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HexFormat;
import java.util.Random;

@ExtendWith(MockitoExtension.class)
class RandomInstructionTest {

    @Mock
    private VRegister vectorXRegister;

    @Mock
    private Random random;

    @InjectMocks
    private RandomInstruction randomInstruction;

    @Test
    void isExecutable_inputNull_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.randomInstruction.isExecutable(null));
    }

    @Test
    void isExecutable_inputEmpty_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.randomInstruction.isExecutable(""));
    }

    @Test
    void isExecutable_inputCommand_returnFalse_test() {
        Assertions.assertFalse(this.randomInstruction.isExecutable("0000"));
    }

    @Test
    void isExecutable_inputCommand_returnTrue_test() {
        Assertions.assertTrue(this.randomInstruction.isExecutable("CFFF"));
    }

    @Test
    void run_inputNull_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.randomInstruction.run(null));
    }

    @Test
    void run_inputEmpty_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.randomInstruction.run(""));
    }

    @Test
    void run_test() {
        Mockito.when(this.random.nextInt()).thenReturn(255);
        this.randomInstruction.run("C1FF");
        Mockito.verify(this.vectorXRegister, Mockito.times(1)).set(1, HexFormat.fromHexDigits("FF"));
    }

}
