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
public class LoadBCDInstructionTest {

    @Mock
    private VRegister vRegister;

    @Mock
    private IndexRegister indexRegister;

    @InjectMocks
    private LoadBCDInstruction loadBCDInstruction;

    @Test
    void isExecutable_inputNull_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.loadBCDInstruction.isExecutable(null));
    }

    @Test
    void isExecutable_inputEmpty_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.loadBCDInstruction.isExecutable(""));
    }

    @Test
    void isExecutable_inputCommand_returnFalse_test() {
        Assertions.assertFalse(this.loadBCDInstruction.isExecutable("0000"));
    }

    @Test
    void isExecutable_inputCommand_returnTrue_test() {
        Assertions.assertTrue(this.loadBCDInstruction.isExecutable("FF33"));
    }

    @Test
    void run_inputNull_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.loadBCDInstruction.run(null));
    }

    @Test
    void run_inputEmpty_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.loadBCDInstruction.run(""));
    }

    @Test
    void run_vRegisterWithMoreThan3Digits_test() {
        Mockito.when(this.vRegister.get(10)).thenReturn(1234);
        Mockito.when(this.indexRegister.get()).thenReturn(1);
        this.loadBCDInstruction.run("FA33");
        Mockito.verify(this.vRegister, Mockito.times(1)).set(2, 1);
        Mockito.verify(this.vRegister, Mockito.times(1)).set(3, 2);
        Mockito.verify(this.vRegister, Mockito.times(1)).set(4, 3);
        Mockito.verifyNoMoreInteractions(this.vRegister);
    }

    @Test
    void run_vRegisterWithMinusThan3Digits_test() {
        Mockito.when(this.vRegister.get(10)).thenReturn(1);
        Mockito.when(this.indexRegister.get()).thenReturn(1);
        this.loadBCDInstruction.run("FA33");
        Mockito.verify(this.vRegister, Mockito.times(1)).set(0, 1);
        Mockito.verify(this.vRegister, Mockito.times(1)).set(0, 2);
        Mockito.verify(this.vRegister, Mockito.times(1)).set(1, 3);
        Mockito.verifyNoMoreInteractions(this.vRegister);
    }

}
