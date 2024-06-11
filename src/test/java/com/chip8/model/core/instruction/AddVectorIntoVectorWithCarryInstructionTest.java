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
class AddVectorIntoVectorWithCarryInstructionTest {

    @Mock
    private VRegister vectorXRegister;

    @Mock
    private VRegister vectorYRegister;

    private AddVectorIntoVectorWithCarryInstruction addVectorIntoVectorWithCarryInstruction;

    @BeforeEach
    void setUp() {
        this.addVectorIntoVectorWithCarryInstruction = new AddVectorIntoVectorWithCarryInstruction(this.vectorXRegister, this.vectorYRegister);
    }

    @Test
    void isExecutable_inputNull_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.addVectorIntoVectorWithCarryInstruction.isExecutable(null));
    }

    @Test
    void isExecutable_inputEmpty_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.addVectorIntoVectorWithCarryInstruction.isExecutable(""));
    }

    @Test
    void isExecutable_inputCommand_returnFalse_test() {
        Assertions.assertFalse(this.addVectorIntoVectorWithCarryInstruction.isExecutable("0000"));
    }

    @Test
    void isExecutable_inputCommand_returnTrue_test() {
        Assertions.assertTrue(this.addVectorIntoVectorWithCarryInstruction.isExecutable("8FF4"));
    }

    @Test
    void run_inputNull_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.addVectorIntoVectorWithCarryInstruction.run(null));
    }

    @Test
    void run_inputEmpty_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.addVectorIntoVectorWithCarryInstruction.run(""));
    }

    @Test
    void run_withCarry_test() {
        Mockito.when(this.vectorXRegister.get(HexFormat.fromHexDigits("A"))).thenReturn(255);
        Mockito.when(this.vectorYRegister.get(HexFormat.fromHexDigits("F"))).thenReturn(1);
        this.addVectorIntoVectorWithCarryInstruction.run("8AF2");
        Mockito.verify(this.vectorXRegister, Mockito.times(1))
                .set(HexFormat.fromHexDigits("A"), 0);
        Mockito.verify(this.vectorXRegister, Mockito.times(1))
                .set(HexFormat.fromHexDigits("F"), 1);
    }

    @Test
    void run_withoutCarry_test() {
        Mockito.when(this.vectorXRegister.get(HexFormat.fromHexDigits("A"))).thenReturn(254);
        Mockito.when(this.vectorYRegister.get(HexFormat.fromHexDigits("F"))).thenReturn(1);
        this.addVectorIntoVectorWithCarryInstruction.run("8AF2");
        Mockito.verify(this.vectorXRegister, Mockito.times(1))
                .set(HexFormat.fromHexDigits("A"), 255);
        Mockito.verify(this.vectorXRegister, Mockito.times(1))
                .set(HexFormat.fromHexDigits("F"), 0);
    }


}
