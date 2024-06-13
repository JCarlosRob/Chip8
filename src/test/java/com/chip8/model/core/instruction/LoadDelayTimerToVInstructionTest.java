package com.chip8.model.core.instruction;

import com.chip8.api.core.register.DelayTimerRegister;
import com.chip8.api.core.register.VRegister;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class LoadDelayTimerToVInstructionTest {

    @Mock
    private VRegister vRegister;

    @Mock
    private DelayTimerRegister delayTimerRegister;

    @InjectMocks
    private LoadDelayTimerToVInstruction loadDelayTimerToVInstruction;

    @Test
    void isExecutable_inputNull_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.loadDelayTimerToVInstruction.isExecutable(null));
    }

    @Test
    void isExecutable_inputEmpty_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.loadDelayTimerToVInstruction.isExecutable(""));
    }

    @Test
    void isExecutable_inputCommand_returnFalse_test() {
        Assertions.assertFalse(this.loadDelayTimerToVInstruction.isExecutable("0000"));
    }

    @Test
    void isExecutable_inputCommand_returnTrue_test() {
        Assertions.assertTrue(this.loadDelayTimerToVInstruction.isExecutable("FA07"));
    }

    @Test
    void run_inputNull_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.loadDelayTimerToVInstruction.run(null));
    }

    @Test
    void run_inputEmpty_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.loadDelayTimerToVInstruction.run(""));
    }

    @Test
    void run_test() {
        Mockito.when(this.delayTimerRegister.get()).thenReturn(1);
        this.loadDelayTimerToVInstruction.run("EA9E");
        Mockito.verify(this.vRegister, Mockito.times(1)).set(10, 1);
    }

}
