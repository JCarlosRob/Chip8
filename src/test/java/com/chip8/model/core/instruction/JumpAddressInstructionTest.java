package com.chip8.model.core.instruction;

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
class JumpAddressInstructionTest {

    @Mock
    private ProgramCounterHandler pc;

    @InjectMocks
    private JumpAddressInstruction jumpAddressInstruction;

    @Test
    void isExecutable_inputNull_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.jumpAddressInstruction.isExecutable(null));
    }

    @Test
    void isExecutable_inputEmpty_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.jumpAddressInstruction.isExecutable(""));
    }

    @Test
    void isExecutable_inputCommand_returnFalse_test() {
        Assertions.assertFalse(this.jumpAddressInstruction.isExecutable("0000"));
    }

    @Test
    void isExecutable_inputCommand_returnTrue_test() {
        Assertions.assertTrue(this.jumpAddressInstruction.isExecutable("1FFF"));
    }

    @Test
    void run_inputNull_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.jumpAddressInstruction.run(null));
    }

    @Test
    void run_inputEmpty_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.jumpAddressInstruction.run(""));
    }

    @Test
    void run_test() {
        this.jumpAddressInstruction.run("1FFF");
        Mockito.verify(this.pc, Mockito.times(1)).set(HexFormat.fromHexDigits("FFF"));
    }

}
