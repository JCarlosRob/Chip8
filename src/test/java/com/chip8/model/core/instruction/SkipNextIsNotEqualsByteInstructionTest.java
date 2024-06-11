package com.chip8.model.core.instruction;

import com.chip8.api.core.register.VRegister;
import com.chip8.model.core.register.ProgramCounterHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HexFormat;

@ExtendWith(MockitoExtension.class)
class SkipNextIsNotEqualsByteInstructionTest {

    @Mock
    private ProgramCounterHandler pc;

    @Mock
    private VRegister vRegister;

    @InjectMocks
    private SkipNextIsNotEqualsByteInstruction skipNextIsNotEqualsByteInstruction;

    @Test
    void isExecutable_inputNull_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.skipNextIsNotEqualsByteInstruction.isExecutable(null));
    }

    @Test
    void isExecutable_inputEmpty_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.skipNextIsNotEqualsByteInstruction.isExecutable(""));
    }

    @Test
    void isExecutable_inputCommand_returnFalse_test() {
        Assertions.assertFalse(this.skipNextIsNotEqualsByteInstruction.isExecutable("0000"));
    }

    @Test
    void isExecutable_inputCommand_returnTrue_test() {
        Assertions.assertTrue(this.skipNextIsNotEqualsByteInstruction.isExecutable("4FFF"));
    }

    @Test
    void run_inputNull_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.skipNextIsNotEqualsByteInstruction.run(null));
    }

    @Test
    void run_inputEmpty_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.skipNextIsNotEqualsByteInstruction.run(""));
    }

    @Test
    void run_vxIsEqualsToData_invokeNext_test() {
        Mockito.when(this.vRegister.get(HexFormat.fromHexDigits("A"))).thenReturn(254);

        this.skipNextIsNotEqualsByteInstruction.run("4AFF");

        Mockito.verify(this.pc, Mockito.times(1)).next(2);
    }

    @Test
    void run_vxIsNotEqualsToData_invokeNext_test() {
        Mockito.when(this.vRegister.get(HexFormat.fromHexDigits("A"))).thenReturn(255);

        this.skipNextIsNotEqualsByteInstruction.run("4AFF");

        Mockito.verifyNoInteractions(this.pc);
    }

}
