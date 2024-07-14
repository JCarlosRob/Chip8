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

@ExtendWith(MockitoExtension.class)
class ReturnFromSubroutineInstructionTest {

    @Mock
    private ProgramCounterHandler pc;

    @Mock
    private MemoryStack memoryStack;

    @InjectMocks
    private ReturnFromSubroutineInstruction returnFromSubroutineInstruction;

    @Test
    void isExecutable_inputNull_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.returnFromSubroutineInstruction.isExecutable(null));
    }

    @Test
    void isExecutable_inputEmpty_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.returnFromSubroutineInstruction.isExecutable(""));
    }

    @Test
    void isExecutable_inputNotExecutable_returnFalse_test() {
        Assertions.assertFalse(this.returnFromSubroutineInstruction.isExecutable("1111"));
    }

    @Test
    void isExecutable_inputExecutable_returnTrue_test() {
        Assertions.assertTrue(this.returnFromSubroutineInstruction.isExecutable("00EE"));
    }

    @Test
    void run_test() {
        Mockito.when(this.memoryStack.pop()).thenReturn(2);
        this.returnFromSubroutineInstruction.run("00EE");
        Mockito.verify(this.pc, Mockito.times(1)).set(2);
        Mockito.verifyNoMoreInteractions(this.memoryStack, this.pc);
    }

}
