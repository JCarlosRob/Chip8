package com.chip8.model.core.instruction;

import com.chip8.api.core.register.TimerRegister;
import com.chip8.api.core.register.VRegister;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SetSoundTimerInstructionTest {

    @Mock
    private VRegister vRegister;

    @Mock
    private TimerRegister soundTimerRegister;

    @InjectMocks
    private SetSoundTimerInstruction setSoundTimerInstruction;

    @Test
    void isExecutable_inputNull_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.setSoundTimerInstruction.isExecutable(null));
    }

    @Test
    void isExecutable_inputEmpty_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.setSoundTimerInstruction.isExecutable(""));
    }

    @Test
    void isExecutable_inputCommand_returnFalse_test() {
        Assertions.assertFalse(this.setSoundTimerInstruction.isExecutable("0000"));
    }

    @Test
    void isExecutable_inputCommand_returnTrue_test() {
        Assertions.assertTrue(this.setSoundTimerInstruction.isExecutable("FA18"));
    }

    @Test
    void run_inputNull_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.setSoundTimerInstruction.run(null));
    }

    @Test
    void run_inputEmpty_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.setSoundTimerInstruction.run(""));
    }

    @Test
    void run_test() {
        Mockito.when(this.vRegister.get(10)).thenReturn(1);
        this.setSoundTimerInstruction.run("FA18");
        Mockito.verify(this.soundTimerRegister, Mockito.times(1)).set(1);
    }

}
