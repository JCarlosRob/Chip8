package com.chip8.model.core.instruction;

import com.chip8.api.core.buffer.Buffer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ClearDisplayInstructionTest {

    @Mock
    private Buffer displayBuffer;

    @InjectMocks
    private ClearDisplayInstruction clearDisplayInstruction;

    @Test
    void isExecutable_inputNull_returnFalse_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.clearDisplayInstruction.isExecutable(null));
    }

    @Test
    void isExecutable_inputEmpty_returnFalse_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.clearDisplayInstruction.isExecutable(""));
    }

    @Test
    void isExecutable_inputCommand_returnFalse_test() {
        Assertions.assertFalse(this.clearDisplayInstruction.isExecutable("0000"));
    }

    @Test
    void isExecutable_inputCommand_returnTrue_test() {
        Assertions.assertTrue(this.clearDisplayInstruction.isExecutable("00E0"));
    }

    @Test
    void run_test() {
        this.clearDisplayInstruction.run("00E0");
        Mockito.verify(this.displayBuffer, Mockito.times(1)).reset();
        Mockito.verifyNoMoreInteractions(this.displayBuffer);
    }

}
