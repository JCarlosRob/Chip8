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

@ExtendWith(MockitoExtension.class)
public class ShiftLeftInstructionTest {

    @Mock
    private VRegister vectorXRegister;

    @InjectMocks
    private ShiftLeftInstruction shiftLeftInstruction;

    @Test
    void isExecutable_inputNull_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.shiftLeftInstruction.isExecutable(null));
    }

    @Test
    void isExecutable_inputEmpty_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.shiftLeftInstruction.isExecutable(""));
    }

    @Test
    void isExecutable_inputCommand_returnFalse_test() {
        Assertions.assertFalse(this.shiftLeftInstruction.isExecutable("0000"));
    }

    @Test
    void isExecutable_inputCommand_returnTrue_test() {
        Assertions.assertTrue(this.shiftLeftInstruction.isExecutable("8FFE"));
    }

    @Test
    void run_inputNull_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.shiftLeftInstruction.run(null));
    }

    @Test
    void run_inputEmpty_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.shiftLeftInstruction.run(""));
    }

    @Test
    void run_vfSet1_test() {
        Mockito.when(this.vectorXRegister.get(HexFormat.fromHexDigits("A"))).thenReturn(128);
        this.shiftLeftInstruction.run("8AF2");
        Mockito.verify(this.vectorXRegister, Mockito.times(1))
                .set(HexFormat.fromHexDigits("A"), 0);
        Mockito.verify(this.vectorXRegister, Mockito.times(1))
                .set(HexFormat.fromHexDigits("F"), 1);
    }

    @Test
    void run_withoutCarry_test() {
        Mockito.when(this.vectorXRegister.get(HexFormat.fromHexDigits("A"))).thenReturn(127);
        this.shiftLeftInstruction.run("8AF2");
        Mockito.verify(this.vectorXRegister, Mockito.times(1))
                .set(HexFormat.fromHexDigits("A"), 254);
        Mockito.verify(this.vectorXRegister, Mockito.times(1))
                .set(HexFormat.fromHexDigits("F"), 0);
    }

}
