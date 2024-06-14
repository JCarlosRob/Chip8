package com.chip8.model.core.instruction;

import com.chip8.api.core.register.IndexRegister;
import com.chip8.api.core.register.VRegister;
import com.chip8.model.core.memory.MemoryRam;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class LoadMemoryToRegistersInstructionTest {

    @Mock
    private VRegister vRegister;

    @Mock
    private IndexRegister indexRegister;

    @Mock
    private MemoryRam memoryRam;

    @InjectMocks
    private LoadMemoryToRegistersInstruction loadMemoryToRegistersInstruction;

    @Test
    void isExecutable_inputNull_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.loadMemoryToRegistersInstruction.isExecutable(null));
    }

    @Test
    void isExecutable_inputEmpty_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.loadMemoryToRegistersInstruction.isExecutable(""));
    }

    @Test
    void isExecutable_inputCommand_returnFalse_test() {
        Assertions.assertFalse(this.loadMemoryToRegistersInstruction.isExecutable("0000"));
    }

    @Test
    void isExecutable_inputCommand_returnTrue_test() {
        Assertions.assertTrue(this.loadMemoryToRegistersInstruction.isExecutable("FA65"));
    }

    @Test
    void run_inputNull_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.loadMemoryToRegistersInstruction.run(null));
    }

    @Test
    void run_inputEmpty_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.loadMemoryToRegistersInstruction.run(""));
    }

    @Test
    void run_multipleStore_test() {
        Mockito.when(this.indexRegister.get()).thenReturn(1);
        Mockito.when(this.memoryRam.read(1)).thenReturn(3);
        Mockito.when(this.memoryRam.read(2)).thenReturn(4);
        this.loadMemoryToRegistersInstruction.run("F165");
        Mockito.verify(this.vRegister, Mockito.times(1)).set(0, 3);
        Mockito.verify(this.vRegister, Mockito.times(1)).set(1, 4);
    }

    @Test
    void run_oneStore_test() {
        Mockito.when(this.indexRegister.get()).thenReturn(1);
        Mockito.when(this.memoryRam.read(1)).thenReturn(3);
        this.loadMemoryToRegistersInstruction.run("F065");
        Mockito.verify(this.vRegister, Mockito.times(1)).set(0, 3);
    }

}
