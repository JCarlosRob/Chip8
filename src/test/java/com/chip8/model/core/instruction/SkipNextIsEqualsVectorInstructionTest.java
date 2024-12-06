package com.chip8.model.core.instruction;

import com.chip8.api.core.register.VRegister;
import com.chip8.model.core.register.ProgramCounterHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HexFormat;

@ExtendWith(MockitoExtension.class)
class SkipNextIsEqualsVectorInstructionTest {

    @Mock
    private ProgramCounterHandler pc;

    @Mock
    private VRegister vRegister;

    private SkipNextIsEqualsVectorInstruction skipNextIsEqualsVectorInstruction;

    @BeforeEach
    public void setUp() {
        this.skipNextIsEqualsVectorInstruction = new SkipNextIsEqualsVectorInstruction(this.pc, this.vRegister);
    }

    @Test
    void isExecutable_inputNull_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.skipNextIsEqualsVectorInstruction.isExecutable(null));
    }

    @Test
    void isExecutable_inputEmpty_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.skipNextIsEqualsVectorInstruction.isExecutable(""));
    }

    @Test
    void isExecutable_inputCommand_returnFalse_test() {
        Assertions.assertFalse(this.skipNextIsEqualsVectorInstruction.isExecutable("0000"));
    }

    @Test
    void isExecutable_inputCommand_returnTrue_test() {
        Assertions.assertTrue(this.skipNextIsEqualsVectorInstruction.isExecutable("5FFF"));
    }

    @Test
    void run_inputNull_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.skipNextIsEqualsVectorInstruction.run(null));
    }

    @Test
    void run_inputEmpty_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.skipNextIsEqualsVectorInstruction.run(""));
    }

    @Test
    void run_vxIsEqualsToVy_invokeNext_test() {
        Mockito.when(this.vRegister.get(HexFormat.fromHexDigits("A"))).thenReturn(255);
        Mockito.when(this.vRegister.get(HexFormat.fromHexDigits("F"))).thenReturn(255);

        this.skipNextIsEqualsVectorInstruction.run("5AFF");

        Mockito.verify(this.pc, Mockito.times(1)).next(2);
    }

    @Test
    void run_vxIsNotEqualsToVy_invokeNext_test() {
        Mockito.when(this.vRegister.get(HexFormat.fromHexDigits("A"))).thenReturn(254);
        Mockito.when(this.vRegister.get(HexFormat.fromHexDigits("F"))).thenReturn(255);

        this.skipNextIsEqualsVectorInstruction.run("5AFF");

        Mockito.verifyNoInteractions(this.pc);
    }

}
