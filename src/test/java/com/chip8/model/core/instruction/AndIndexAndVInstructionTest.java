package com.chip8.model.core.instruction;

import com.chip8.api.core.register.IndexRegister;
import com.chip8.api.core.register.VRegister;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AndIndexAndVInstructionTest {

    @Mock
    private VRegister vRegister;

    @Mock
    private IndexRegister indexRegister;

    @InjectMocks
    private AddIndexAndVInstruction addIndexAndVInstruction;

    @Test
    void isExecutable_inputNull_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.addIndexAndVInstruction.isExecutable(null));
    }

    @Test
    void isExecutable_inputEmpty_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.addIndexAndVInstruction.isExecutable(""));
    }

    @Test
    void isExecutable_inputCommand_returnFalse_test() {
        Assertions.assertFalse(this.addIndexAndVInstruction.isExecutable("8000"));
    }

    @Test
    void isExecutable_inputCommand_returnTrue_test() {
        Assertions.assertTrue(this.addIndexAndVInstruction.isExecutable("FF1E"));
    }

    @Test
    void run_inputNull_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.addIndexAndVInstruction.run(null));
    }

    @Test
    void run_inputEmpty_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.addIndexAndVInstruction.run(""));
    }

    @Test
    void run_test() {
        Mockito.when(this.indexRegister.get()).thenReturn(1);
        Mockito.when(this.vRegister.get(10)).thenReturn(2);
        this.addIndexAndVInstruction.run("FA1E");
        Mockito.verify(this.indexRegister, Mockito.times(1)).set(3);
    }

}
