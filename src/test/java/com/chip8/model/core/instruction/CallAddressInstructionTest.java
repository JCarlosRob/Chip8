package com.chip8.model.core.instruction;

import com.chip8.api.core.memory.MemoryStack;
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
class CallAddressInstructionTest {

    @Mock
    private ProgramCounterHandler pc;

    @Mock
    private MemoryStack memoryStack;

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
        Mockito.when(this.pc.get()).thenReturn(2);

        this.callAddressInstruction.run("2FFF");

        Mockito.verify(this.memoryStack, Mockito.times(1)).push(2);
        Mockito.verify(this.pc, Mockito.times(1)).set(HexFormat.fromHexDigits("2FFF".substring(1)));
        Mockito.verifyNoMoreInteractions(this.pc, this.memoryStack);
    }

}
