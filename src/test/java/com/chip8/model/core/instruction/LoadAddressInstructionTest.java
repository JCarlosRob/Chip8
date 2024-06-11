package com.chip8.model.core.instruction;

import com.chip8.api.core.register.IndexRegister;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HexFormat;

@ExtendWith(MockitoExtension.class)
class LoadAddressInstructionTest {

    @Mock
    private IndexRegister indexRegister;

    @InjectMocks
    private LoadAddressInstruction loadAddressInstruction;

    @Test
    void isExecutable_inputNull_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.loadAddressInstruction.isExecutable(null));
    }

    @Test
    void isExecutable_inputEmpty_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.loadAddressInstruction.isExecutable(""));
    }

    @Test
    void isExecutable_inputCommand_returnFalse_test() {
        Assertions.assertFalse(this.loadAddressInstruction.isExecutable("0000"));
    }

    @Test
    void isExecutable_inputCommand_returnTrue_test() {
        Assertions.assertTrue(this.loadAddressInstruction.isExecutable("AFFF"));
    }

    @Test
    void run_inputNull_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.loadAddressInstruction.run(null));
    }

    @Test
    void run_inputEmpty_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.loadAddressInstruction.run(""));
    }

    @Test
    void run_test() {
        this.loadAddressInstruction.run("AFFF");
        Mockito.verify(this.indexRegister, Mockito.times(1)).set(HexFormat.fromHexDigits("FFF"));
    }

}
