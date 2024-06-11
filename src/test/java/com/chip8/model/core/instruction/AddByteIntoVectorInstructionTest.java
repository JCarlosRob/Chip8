package com.chip8.model.core.instruction;

import com.chip8.api.core.register.VRegister;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HexFormat;

@ExtendWith(MockitoExtension.class)
class AddByteIntoVectorInstructionTest {

    @Mock
    private VRegister vectorXRegister;

    @InjectMocks
    private AddByteIntoVectorInstruction addByteIntoVectorInstruction;

    @Test
    void isExecutable_inputNull_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.addByteIntoVectorInstruction.isExecutable(null));
    }

    @Test
    void isExecutable_inputEmpty_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.addByteIntoVectorInstruction.isExecutable(""));
    }

    @Test
    void isExecutable_inputCommand_returnFalse_test() {
        Assertions.assertFalse(this.addByteIntoVectorInstruction.isExecutable("0000"));
    }

    @Test
    void isExecutable_inputCommand_returnTrue_test() {
        Assertions.assertTrue(this.addByteIntoVectorInstruction.isExecutable("7FFF"));
    }

    @Test
    void run_inputNull_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.addByteIntoVectorInstruction.run(null));
    }

    @Test
    void run_inputEmpty_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.addByteIntoVectorInstruction.run(""));
    }

    @Test
    void run_test() {
        this.addByteIntoVectorInstruction.run("6AFF");
        Mockito.verify(this.vectorXRegister, Mockito.times(1))
                .add(HexFormat.fromHexDigits("A"), HexFormat.fromHexDigits("FF"));
    }

}
