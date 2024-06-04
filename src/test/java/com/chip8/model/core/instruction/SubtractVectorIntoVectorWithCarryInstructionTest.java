package com.chip8.model.core.instruction;

import com.chip8.api.core.register.VectorRegister;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HexFormat;

@ExtendWith(MockitoExtension.class)
class SubtractVectorIntoVectorWithCarryInstructionTest {

    @Mock
    private VectorRegister vectorXRegister;

    @Mock
    private VectorRegister vectorYRegister;

    private SubtractVectorIntoVectorWithCarryInstruction subtractVectorIntoVectorWithCarryInstruction;

    @BeforeEach
    void setUp() {
        this.subtractVectorIntoVectorWithCarryInstruction = new SubtractVectorIntoVectorWithCarryInstruction(this.vectorXRegister, this.vectorYRegister);
    }

    @Test
    void isExecutable_inputNull_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.subtractVectorIntoVectorWithCarryInstruction.isExecutable(null));
    }

    @Test
    void isExecutable_inputEmpty_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.subtractVectorIntoVectorWithCarryInstruction.isExecutable(""));
    }

    @Test
    void isExecutable_inputCommand_returnFalse_test() {
        Assertions.assertFalse(this.subtractVectorIntoVectorWithCarryInstruction.isExecutable("0000"));
    }

    @Test
    void isExecutable_inputCommand_returnTrue_test() {
        Assertions.assertTrue(this.subtractVectorIntoVectorWithCarryInstruction.isExecutable("8FF5"));
    }

    @Test
    void run_inputNull_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.subtractVectorIntoVectorWithCarryInstruction.run(null));
    }

    @Test
    void run_inputEmpty_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.subtractVectorIntoVectorWithCarryInstruction.run(""));
    }

    @Test
    void run_withBorrow_test() {
        Mockito.when(this.vectorXRegister.getVRegister(HexFormat.fromHexDigits("A"))).thenReturn(1);
        Mockito.when(this.vectorYRegister.getVRegister(HexFormat.fromHexDigits("F"))).thenReturn(0);
        this.subtractVectorIntoVectorWithCarryInstruction.run("8AF2");
        Mockito.verify(this.vectorXRegister, Mockito.times(1))
                .setVRegister(HexFormat.fromHexDigits("A"), 1);
        Mockito.verify(this.vectorXRegister, Mockito.times(1))
                .setVRegister(HexFormat.fromHexDigits("F"), 1);
    }

    @Test
    void run_withoutBorrow_test() {
        Mockito.when(this.vectorXRegister.getVRegister(HexFormat.fromHexDigits("A"))).thenReturn(0);
        Mockito.when(this.vectorYRegister.getVRegister(HexFormat.fromHexDigits("F"))).thenReturn(1);
        this.subtractVectorIntoVectorWithCarryInstruction.run("8AF2");
        Mockito.verify(this.vectorXRegister, Mockito.times(1))
                .setVRegister(HexFormat.fromHexDigits("A"), -1);
        Mockito.verify(this.vectorXRegister, Mockito.times(1))
                .setVRegister(HexFormat.fromHexDigits("F"), 0);
    }


}
