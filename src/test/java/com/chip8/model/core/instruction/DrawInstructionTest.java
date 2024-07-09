package com.chip8.model.core.instruction;

import com.chip8.api.core.graphics.GraphicController;
import com.chip8.api.core.memory.Memory;
import com.chip8.api.core.register.IndexRegister;
import com.chip8.api.core.register.VRegister;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HexFormat;

@ExtendWith(MockitoExtension.class)
class DrawInstructionTest {

    @Mock
    private VRegister vRegister;

    @Mock
    private Memory memoryRam;

    @Mock
    private IndexRegister indexRegister;

    @Mock
    private GraphicController graphicController;

    private DrawInstruction drawInstruction;

    @BeforeEach
    void setUp() {
        this.drawInstruction = new DrawInstruction(this.vRegister, this.memoryRam, this.indexRegister, this.graphicController);
    }

    @Test
    void isExecutable_inputNull_returnFalse_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.drawInstruction.isExecutable(null));
    }

    @Test
    void isExecutable_inputEmpty_returnFalse_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.drawInstruction.isExecutable(""));
    }

    @Test
    void isExecutable_inputCommand_returnFalse_test() {
        Assertions.assertFalse(this.drawInstruction.isExecutable("0000"));
    }

    @Test
    void isExecutable_inputCommand_returnTrue_test() {
        Assertions.assertTrue(this.drawInstruction.isExecutable("D0E0"));
    }

    @Test
    void run_collisionDetected_test() {
        Mockito.when(this.vRegister.get(HexFormat.fromHexDigits("A"))).thenReturn(1);
        Mockito.when(this.vRegister.get(HexFormat.fromHexDigits("B"))).thenReturn(2);
        Mockito.when(this.indexRegister.get()).thenReturn(HexFormat.fromHexDigits("FF"));
        Mockito.when(this.memoryRam.read(HexFormat.fromHexDigits("FF"), HexFormat.fromHexDigits("10E"))).thenReturn(new Integer[]{1, 0, 0});

        this.drawInstruction.run("DABF");

//        Mockito.verify(this.vRegister, Mockito.times(1)).set(15, 1);
    }

    @Test
    void run_collisionNotDetected_test() {
        Mockito.when(this.vRegister.get(HexFormat.fromHexDigits("A"))).thenReturn(1);
        Mockito.when(this.vRegister.get(HexFormat.fromHexDigits("B"))).thenReturn(2);
        Mockito.when(this.indexRegister.get()).thenReturn(HexFormat.fromHexDigits("FF"));
        Mockito.when(this.memoryRam.read(HexFormat.fromHexDigits("FF"), HexFormat.fromHexDigits("10E"))).thenReturn(new Integer[]{0, 0, 0});

        this.drawInstruction.run("DABF");

//        Mockito.verify(this.vRegister, Mockito.times(1)).set(15, 0);
    }

    @Test
    void run_collisionDetected_withOverflow_test() {
        Mockito.when(this.vRegister.get(HexFormat.fromHexDigits("A"))).thenReturn(62);
        Mockito.when(this.vRegister.get(HexFormat.fromHexDigits("B"))).thenReturn(2);
        Mockito.when(this.indexRegister.get()).thenReturn(HexFormat.fromHexDigits("FF"));
        Mockito.when(this.memoryRam.read(HexFormat.fromHexDigits("FF"), HexFormat.fromHexDigits("10E"))).thenReturn(new Integer[]{1, 0, 0});

        this.drawInstruction.run("DABF");

//        Mockito.verify(this.vRegister, Mockito.times(1)).set(15, 1);
    }

    @Test
    void run_collisionNotDetected_withOverflow_test() {
        Mockito.when(this.vRegister.get(HexFormat.fromHexDigits("A"))).thenReturn(62);
        Mockito.when(this.vRegister.get(HexFormat.fromHexDigits("B"))).thenReturn(2);
        Mockito.when(this.indexRegister.get()).thenReturn(HexFormat.fromHexDigits("FF"));
        Mockito.when(this.memoryRam.read(HexFormat.fromHexDigits("FF"), HexFormat.fromHexDigits("10E"))).thenReturn(new Integer[]{0, 0, 0});

        this.drawInstruction.run("DABF");

//        Mockito.verify(this.vRegister, Mockito.times(1)).set(15, 0);
    }

}
