package com.chip8.model.core.instruction;

import com.chip8.api.core.buffer.Buffer;
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
class SetILocationOfSpriteInstructionTest {

    @Mock
    private VRegister vRegister;

    @Mock
    private Buffer displayBuffer;

    @Mock
    private IndexRegister indexRegister;

    @InjectMocks
    private SetILocationOfSpriteInstruction setILocationOfSpriteInstruction;

    @Test
    void isExecutable_inputNull_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.setILocationOfSpriteInstruction.isExecutable(null));
    }

    @Test
    void isExecutable_inputEmpty_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.setILocationOfSpriteInstruction.isExecutable(""));
    }

    @Test
    void isExecutable_inputCommand_returnFalse_test() {
        Assertions.assertFalse(this.setILocationOfSpriteInstruction.isExecutable("0000"));
    }

    @Test
    void isExecutable_inputCommand_returnTrue_test() {
        Assertions.assertTrue(this.setILocationOfSpriteInstruction.isExecutable("FA29"));
    }

    @Test
    void run_inputNull_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.setILocationOfSpriteInstruction.run(null));
    }

    @Test
    void run_inputEmpty_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.setILocationOfSpriteInstruction.run(""));
    }

    @Test
    void run_maxLength_test() {
        Mockito.when(this.vRegister.get(10)).thenReturn(2048);
        this.setILocationOfSpriteInstruction.run("FA29");
    }

    @Test
    void run_minLength_test() {
        Mockito.when(this.vRegister.get(10)).thenReturn(0);
        this.setILocationOfSpriteInstruction.run("FA29");
    }

}
