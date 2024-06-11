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
class LoadVectorIntoVectorInstructionTest {

    @Mock
    private VRegister vRegister;

    @InjectMocks
    private LoadVectorIntoVectorInstruction loadVectorIntoVectorInstruction;

    @Test
    void isExecutable_inputNull_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.loadVectorIntoVectorInstruction.isExecutable(null));
    }

    @Test
    void isExecutable_inputEmpty_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.loadVectorIntoVectorInstruction.isExecutable(""));
    }

    @Test
    void isExecutable_inputCommand_returnFalse_test() {
        Assertions.assertFalse(this.loadVectorIntoVectorInstruction.isExecutable("8001"));
    }

    @Test
    void isExecutable_inputCommand_returnTrue_test() {
        Assertions.assertTrue(this.loadVectorIntoVectorInstruction.isExecutable("8FF0"));
    }

    @Test
    void run_inputNull_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.loadVectorIntoVectorInstruction.run(null));
    }

    @Test
    void run_inputEmpty_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.loadVectorIntoVectorInstruction.run(""));
    }

    @Test
    void run_vyEqualsVx_test() {
        Mockito.when(this.vRegister.get(HexFormat.fromHexDigits("A"))).thenReturn(1);
        Mockito.when(this.vRegister.get(HexFormat.fromHexDigits("F"))).thenReturn(1);
        this.loadVectorIntoVectorInstruction.run("8AF0");
        Mockito.verify(this.vRegister, Mockito.times(1))
                .set(HexFormat.fromHexDigits("A"), 1);
    }

    @Test
    void run_vyNotEqualsVx_test() {
        Mockito.when(this.vRegister.get(HexFormat.fromHexDigits("A"))).thenReturn(1);
        Mockito.when(this.vRegister.get(HexFormat.fromHexDigits("F"))).thenReturn(2);
        this.loadVectorIntoVectorInstruction.run("8AF0");
        Mockito.verifyNoMoreInteractions(this.vRegister);
    }

}
