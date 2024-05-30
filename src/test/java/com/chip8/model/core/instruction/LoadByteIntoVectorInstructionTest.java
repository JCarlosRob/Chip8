package com.chip8.model.core.instruction;

import com.chip8.api.core.register.VectorRegister;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HexFormat;

@ExtendWith(MockitoExtension.class)
class LoadByteIntoVectorInstructionTest {

    @Mock
    private VectorRegister vectorXRegister;

    @InjectMocks
    private LoadByteIntoVectorInstruction loadByteIntoVectorInstruction;

    @Test
    void isExecutable_inputNull_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.loadByteIntoVectorInstruction.isExecutable(null));
    }

    @Test
    void isExecutable_inputEmpty_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.loadByteIntoVectorInstruction.isExecutable(""));
    }

    @Test
    void isExecutable_inputCommand_returnFalse_test() {
        Assertions.assertFalse(this.loadByteIntoVectorInstruction.isExecutable("0000"));
    }

    @Test
    void isExecutable_inputCommand_returnTrue_test() {
        Assertions.assertTrue(this.loadByteIntoVectorInstruction.isExecutable("6FFF"));
    }

    @Test
    void run_inputNull_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.loadByteIntoVectorInstruction.run(null));
    }

    @Test
    void run_inputEmpty_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.loadByteIntoVectorInstruction.run(""));
    }

    @Test
    void run_test() {
        this.loadByteIntoVectorInstruction.run("6AFF");
        Mockito.verify(this.vectorXRegister, Mockito.times(1))
                .setVRegister(HexFormat.fromHexDigits("A"), HexFormat.fromHexDigits("FF"));
    }

}
