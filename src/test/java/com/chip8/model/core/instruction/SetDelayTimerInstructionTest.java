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
class SetDelayTimerInstructionTest {

    @Mock
    private VRegister vRegister;

    @Mock
    private DelayTimerRegister delayTimerRegister;

    @InjectMocks
    private SetDelayTimerInstruction setDelayTimerInstruction;

    @Test
    void isExecutable_inputNull_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.setDelayTimerInstruction.isExecutable(null));
    }

    @Test
    void isExecutable_inputEmpty_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.setDelayTimerInstruction.isExecutable(""));
    }

    @Test
    void isExecutable_inputCommand_returnFalse_test() {
        Assertions.assertFalse(this.setDelayTimerInstruction.isExecutable("0000"));
    }

    @Test
    void isExecutable_inputCommand_returnTrue_test() {
        Assertions.assertTrue(this.setDelayTimerInstruction.isExecutable("FA15"));
    }

    @Test
    void run_inputNull_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.setDelayTimerInstruction.run(null));
    }

    @Test
    void run_inputEmpty_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.setDelayTimerInstruction.run(""));
    }

    @Test
    void run_test() {
        Mockito.when(this.vRegister.get(10)).thenReturn(1);
        this.setDelayTimerInstruction.run("FA15");
        Mockito.verify(this.delayTimerRegister, Mockito.times(1)).set(1);
    }

}
