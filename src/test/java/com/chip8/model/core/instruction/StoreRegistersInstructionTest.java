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
public class StoreRegistersInstructionTest {

    @Mock
    private VRegister vRegister;

    @Mock
    private IndexRegister indexRegister;

    @Mock
    private MemoryRam memoryRam;

    @InjectMocks
    private StoreRegistersInstruction storeRegistersInstruction;

    @Test
    void isExecutable_inputNull_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.storeRegistersInstruction.isExecutable(null));
    }

    @Test
    void isExecutable_inputEmpty_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.storeRegistersInstruction.isExecutable(""));
    }

    @Test
    void isExecutable_inputCommand_returnFalse_test() {
        Assertions.assertFalse(this.storeRegistersInstruction.isExecutable("0000"));
    }

    @Test
    void isExecutable_inputCommand_returnTrue_test() {
        Assertions.assertTrue(this.storeRegistersInstruction.isExecutable("FA55"));
    }

    @Test
    void run_inputNull_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.storeRegistersInstruction.run(null));
    }

    @Test
    void run_inputEmpty_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.storeRegistersInstruction.run(""));
    }

    @Test
    void run_multipleStore_test() {
        Mockito.when(this.indexRegister.get()).thenReturn(1);
        Mockito.when(this.vRegister.get(0)).thenReturn(3);
        Mockito.when(this.vRegister.get(1)).thenReturn(4);
        this.storeRegistersInstruction.run("F155");
        Mockito.verify(this.memoryRam, Mockito.times(1)).write(1, 3);
        Mockito.verify(this.memoryRam, Mockito.times(1)).write(2, 4);
    }

    @Test
    void run_oneStore_test() {
        Mockito.when(this.indexRegister.get()).thenReturn(1);
        Mockito.when(this.vRegister.get(0)).thenReturn(3);
        this.storeRegistersInstruction.run("F055");
        Mockito.verify(this.memoryRam, Mockito.times(1)).write(1, 3);
    }

}
