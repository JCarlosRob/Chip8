package com.chip8.model.core.instruction;

import com.chip8.api.core.memory.Memory;
import com.chip8.model.core.register.ProgramCounterHandler;
import com.chip8.model.core.register.StackPointerHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HexFormat;

@ExtendWith(MockitoExtension.class)
class CallAddressInstructionTest {

    @Mock
    private StackPointerHandler sp;

    @Mock
    private ProgramCounterHandler pc;

    @Mock
    private Memory memoryStack;

    @InjectMocks
    private CallAddressInstruction callAddressInstruction;

    @Test
    void isExecutable_inputNull_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.callAddressInstruction.isExecutable(null));
    }

    @Test
    void isExecutable_inputEmpty_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.callAddressInstruction.isExecutable(""));
    }

    @Test
    void isExecutable_inputNotExecutable_returnFalse_test() {
        Assertions.assertFalse(this.callAddressInstruction.isExecutable("1111"));
    }

    @Test
    void isExecutable_inputExecutable_returnTrue_test() {
        Assertions.assertTrue(this.callAddressInstruction.isExecutable("2FFF"));
    }

    @Test
    void run_test() {
        Mockito.when(this.sp.get()).thenReturn(1);
        Mockito.when(this.pc.get()).thenReturn(2);

        this.callAddressInstruction.run("2FFF");

        Mockito.verify(this.memoryStack, Mockito.times(1)).write(1, 2);
        Mockito.verify(this.sp, Mockito.times(1)).increase();
        Mockito.verify(this.pc, Mockito.times(1)).set(HexFormat.fromHexDigits("2FFF".substring(1)));
    }

}
