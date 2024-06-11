package com.chip8.model.core.instruction;

import com.chip8.api.core.register.VRegister;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HexFormat;

@ExtendWith(MockitoExtension.class)
public class SubtractVyFromVxInstructionTest {

    @Mock
    private VRegister vectorXRegister;

    @Mock
    private VRegister vectorYRegister;

    private SubtractVyFromVxInstruction subtractVyFromVxInstruction;

    @BeforeEach
    void setUp() {
        this.subtractVyFromVxInstruction = new SubtractVyFromVxInstruction(this.vectorXRegister, this.vectorYRegister);
    }

    @Test
    void isExecutable_inputNull_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.subtractVyFromVxInstruction.isExecutable(null));
    }

    @Test
    void isExecutable_inputEmpty_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.subtractVyFromVxInstruction.isExecutable(""));
    }

    @Test
    void isExecutable_inputCommand_returnFalse_test() {
        Assertions.assertFalse(this.subtractVyFromVxInstruction.isExecutable("0000"));
    }

    @Test
    void isExecutable_inputCommand_returnTrue_test() {
        Assertions.assertTrue(this.subtractVyFromVxInstruction.isExecutable("8FF7"));
    }

    @Test
    void run_inputNull_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.subtractVyFromVxInstruction.run(null));
    }

    @Test
    void run_inputEmpty_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.subtractVyFromVxInstruction.run(""));
    }

    @Test
    void run_withBorrow_test() {
        Mockito.when(this.vectorXRegister.get(HexFormat.fromHexDigits("A"))).thenReturn(0);
        Mockito.when(this.vectorYRegister.get(HexFormat.fromHexDigits("F"))).thenReturn(1);
        this.subtractVyFromVxInstruction.run("8AF7");
        Mockito.verify(this.vectorXRegister, Mockito.times(1))
                .set(HexFormat.fromHexDigits("A"), 1);
        Mockito.verify(this.vectorXRegister, Mockito.times(1))
                .set(HexFormat.fromHexDigits("F"), 1);
    }

    @Test
    void run_withoutBorrow_test() {
        Mockito.when(this.vectorXRegister.get(HexFormat.fromHexDigits("A"))).thenReturn(1);
        Mockito.when(this.vectorYRegister.get(HexFormat.fromHexDigits("F"))).thenReturn(0);
        this.subtractVyFromVxInstruction.run("8AF2");
        Mockito.verify(this.vectorXRegister, Mockito.times(1))
                .set(HexFormat.fromHexDigits("A"), -1);
        Mockito.verify(this.vectorXRegister, Mockito.times(1))
                .set(HexFormat.fromHexDigits("F"), 0);
    }

}
