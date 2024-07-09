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
class SkipNextIsNotEqualsVectorInstructionTest {

    @Mock
    private ProgramCounterHandler pc;

    @Mock
    private VRegister vRegister;

    private SkipNextIsNotEqualsVectorInstruction skipNextIsNotEqualsVectorInstruction;

    @BeforeEach
    public void setUp() {
        this.skipNextIsNotEqualsVectorInstruction = new SkipNextIsNotEqualsVectorInstruction(this.pc, this.vRegister);
    }

    @Test
    void isExecutable_inputNull_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.skipNextIsNotEqualsVectorInstruction.isExecutable(null));
    }

    @Test
    void isExecutable_inputEmpty_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.skipNextIsNotEqualsVectorInstruction.isExecutable(""));
    }

    @Test
    void isExecutable_inputCommand_returnFalse_test() {
        Assertions.assertFalse(this.skipNextIsNotEqualsVectorInstruction.isExecutable("0000"));
    }

    @Test
    void isExecutable_inputCommand_returnTrue_test() {
        Assertions.assertTrue(this.skipNextIsNotEqualsVectorInstruction.isExecutable("9FF0"));
    }

    @Test
    void run_inputNull_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.skipNextIsNotEqualsVectorInstruction.run(null));
    }

    @Test
    void run_inputEmpty_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.skipNextIsNotEqualsVectorInstruction.run(""));
    }

    @Test
    void run_vxIsEqualsToData_invokeNext_test() {
        Mockito.when(this.vRegister.get(HexFormat.fromHexDigits("A"))).thenReturn(254);
        Mockito.when(this.vRegister.get(HexFormat.fromHexDigits("F"))).thenReturn(253);
        this.skipNextIsNotEqualsVectorInstruction.run("9AF0");
        Mockito.verify(this.pc, Mockito.times(1)).next(4);
    }

    @Test
    void run_vxIsNotEqualsToData_invokeNext_test() {
        Mockito.when(this.vRegister.get(HexFormat.fromHexDigits("A"))).thenReturn(254);
        Mockito.when(this.vRegister.get(HexFormat.fromHexDigits("F"))).thenReturn(254);
        this.skipNextIsNotEqualsVectorInstruction.run("9AF0");
        Mockito.verifyNoInteractions(this.pc);
    }

}
