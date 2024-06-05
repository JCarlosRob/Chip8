package com.chip8.model.core.instruction;

import com.chip8.api.core.register.VectorRegister;
import com.chip8.model.core.register.ProgramCounterHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HexFormat;

@ExtendWith(MockitoExtension.class)
public class JumpAddressMoreVectorInstructionTest {

    @Mock
    private ProgramCounterHandler pc;

    @Mock
    private VectorRegister vectorXRegister;

    private JumpAddressMoreVectorInstruction jumpAddressMoreVectorInstruction;

    @BeforeEach
    public void setUp() {
        this.jumpAddressMoreVectorInstruction = new JumpAddressMoreVectorInstruction(this.pc, this.vectorXRegister);
    }

    @Test
    void isExecutable_inputNull_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.jumpAddressMoreVectorInstruction.isExecutable(null));
    }

    @Test
    void isExecutable_inputEmpty_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.jumpAddressMoreVectorInstruction.isExecutable(""));
    }

    @Test
    void isExecutable_inputCommand_returnFalse_test() {
        Assertions.assertFalse(this.jumpAddressMoreVectorInstruction.isExecutable("0000"));
    }

    @Test
    void isExecutable_inputCommand_returnTrue_test() {
        Assertions.assertTrue(this.jumpAddressMoreVectorInstruction.isExecutable("BFFF"));
    }

    @Test
    void run_inputNull_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.jumpAddressMoreVectorInstruction.run(null));
    }

    @Test
    void run_inputEmpty_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.jumpAddressMoreVectorInstruction.run(""));
    }

    @Test
    void run_test() {
        Mockito.when(this.vectorXRegister.getVRegister(0)).thenReturn(1);
        this.jumpAddressMoreVectorInstruction.run("BFFE");
        Mockito.verify(this.pc, Mockito.times(1)).setPc(HexFormat.fromHexDigits("FFF"));
    }

}
