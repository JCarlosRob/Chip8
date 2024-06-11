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
class XorVectorInstructionTest {

    @Mock
    private VRegister vRegister;

    @InjectMocks
    private XorVectorInstruction orVectorInstruction;

    @Test
    void isExecutable_inputNull_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.orVectorInstruction.isExecutable(null));
    }

    @Test
    void isExecutable_inputEmpty_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.orVectorInstruction.isExecutable(""));
    }

    @Test
    void isExecutable_inputCommand_returnFalse_test() {
        Assertions.assertFalse(this.orVectorInstruction.isExecutable("8000"));
    }

    @Test
    void isExecutable_inputCommand_returnTrue_test() {
        Assertions.assertTrue(this.orVectorInstruction.isExecutable("8FF3"));
    }

    @Test
    void run_inputNull_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.orVectorInstruction.run(null));
    }

    @Test
    void run_inputEmpty_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.orVectorInstruction.run(""));
    }

    @Test
    void run_test() {
        Mockito.when(this.vRegister.get(HexFormat.fromHexDigits("A"))).thenReturn(1);
        Mockito.when(this.vRegister.get(HexFormat.fromHexDigits("F"))).thenReturn(2);
        this.orVectorInstruction.run("8AF2");
        Mockito.verify(this.vRegister, Mockito.times(1))
                .set(HexFormat.fromHexDigits("A"), 3);
    }

}
