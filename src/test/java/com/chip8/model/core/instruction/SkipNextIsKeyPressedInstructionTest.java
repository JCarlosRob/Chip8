package com.chip8.model.core.instruction;

import com.chip8.api.core.register.ProgramCounter;
import com.chip8.api.core.register.VRegister;
import com.chip8.api.keyboard.Keyboard;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HexFormat;

@ExtendWith(MockitoExtension.class)
class SkipNextIsKeyPressedInstructionTest {

    @Mock
    private ProgramCounter pc;

    @Mock
    private VRegister vRegister;

    @Mock
    private Keyboard keyboard;

    @InjectMocks
    private SkipNextIsKeyPressedInstruction skipNextIsKeyPressedInstruction;

    @Test
    void isExecutable_inputNull_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.skipNextIsKeyPressedInstruction.isExecutable(null));
    }

    @Test
    void isExecutable_inputEmpty_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.skipNextIsKeyPressedInstruction.isExecutable(""));
    }

    @Test
    void isExecutable_inputCommand_returnFalse_test() {
        Assertions.assertFalse(this.skipNextIsKeyPressedInstruction.isExecutable("0000"));
    }

    @Test
    void isExecutable_inputCommand_returnTrue_test() {
        Assertions.assertTrue(this.skipNextIsKeyPressedInstruction.isExecutable("EA9E"));
    }

    @Test
    void run_inputNull_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.skipNextIsKeyPressedInstruction.run(null));
    }

    @Test
    void run_inputEmpty_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.skipNextIsKeyPressedInstruction.run(""));
    }

    @Test
    void run_thePressedKeyIsEquals_invokeNext_test() {
        Mockito.when(this.vRegister.get(HexFormat.fromHexDigits("A"))).thenReturn(10);
        Mockito.when(this.keyboard.read()).thenReturn("A");
        this.skipNextIsKeyPressedInstruction.run("EA9E");
        Mockito.verify(this.pc, Mockito.times(1)).next(2);
    }

    @Test
    void run_thePressedKeyIsNotEquals_notInvokeNext_test() {
        Mockito.when(this.vRegister.get(HexFormat.fromHexDigits("A"))).thenReturn(10);
        Mockito.when(this.keyboard.read()).thenReturn("B");
        this.skipNextIsKeyPressedInstruction.run("EA93");
        Mockito.verifyNoInteractions(this.pc);
    }

}
