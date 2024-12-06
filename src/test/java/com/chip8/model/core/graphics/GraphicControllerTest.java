package com.chip8.model.core.graphics;

import com.chip8.api.core.buffer.Buffer;
import com.chip8.api.core.register.VRegister;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@Disabled
class GraphicControllerTest {

    @Mock
    private Buffer displayBuffer;

    @Mock
    private VRegister vRegister;

    @InjectMocks
    private GraphicControllerHandler graphicController;

    @Test
    void display_inputXNull_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.graphicController.display(null, 0, new Integer[]{1}));
    }

    @Test
    void display_inputYNull_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.graphicController.display(0, null, new Integer[]{1}));
    }

    @Test
    void display_inputDataNull_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.graphicController.display(0, 0, null));
    }

    @Test
    void display_inputSpriteWithoutCollision_test() {
        Mockito.when(this.displayBuffer.read(0, 0)).thenReturn(0);
        Mockito.when(this.displayBuffer.read(1, 0)).thenReturn(0);
        Mockito.when(this.displayBuffer.read(2, 0)).thenReturn(0);
        Mockito.when(this.displayBuffer.read(3, 0)).thenReturn(0);
        Mockito.when(this.displayBuffer.read(4, 0)).thenReturn(0);
        Mockito.when(this.displayBuffer.read(5, 0)).thenReturn(0);
        Mockito.when(this.displayBuffer.read(6, 0)).thenReturn(0);
        Mockito.when(this.displayBuffer.read(7, 0)).thenReturn(0);

        this.graphicController.display(0, 0, new Integer[]{255});

        Mockito.verify(this.vRegister, Mockito.times(1)).set(15, 0);
        Mockito.verify(this.vRegister, Mockito.never()).set(15, 1);
        Mockito.verify(this.vRegister, Mockito.times(8)).get(15);

        Mockito.verify(this.displayBuffer, Mockito.times(1)).write(0, 0, 1);
        Mockito.verify(this.displayBuffer, Mockito.times(1)).write(1, 0, 1);
        Mockito.verify(this.displayBuffer, Mockito.times(1)).write(2, 0, 1);
        Mockito.verify(this.displayBuffer, Mockito.times(1)).write(3, 0, 1);
        Mockito.verify(this.displayBuffer, Mockito.times(1)).write(4, 0, 1);
        Mockito.verify(this.displayBuffer, Mockito.times(1)).write(5, 0, 1);
        Mockito.verify(this.displayBuffer, Mockito.times(1)).write(6, 0, 1);
        Mockito.verify(this.displayBuffer, Mockito.times(1)).write(7, 0, 1);

        Mockito.verifyNoMoreInteractions(this.vRegister, this.displayBuffer);
    }

    @Test
    void display_inputSpriteWithCollision_test() {
        Mockito.when(this.displayBuffer.read(0, 0)).thenReturn(1);
        Mockito.when(this.displayBuffer.read(1, 0)).thenReturn(1);
        Mockito.when(this.displayBuffer.read(2, 0)).thenReturn(1);
        Mockito.when(this.displayBuffer.read(3, 0)).thenReturn(1);
        Mockito.when(this.displayBuffer.read(4, 0)).thenReturn(1);
        Mockito.when(this.displayBuffer.read(5, 0)).thenReturn(1);
        Mockito.when(this.displayBuffer.read(6, 0)).thenReturn(1);
        Mockito.when(this.displayBuffer.read(7, 0)).thenReturn(1);

        Mockito.when(this.vRegister.get(15)).thenReturn(0, 1, 1, 1, 1, 1, 1, 1);

        this.graphicController.display(0, 0, new Integer[]{1});

        Mockito.verify(this.vRegister, Mockito.times(1)).set(15, 0);
        Mockito.verify(this.vRegister, Mockito.times(1)).set(15, 1);
        Mockito.verify(this.vRegister, Mockito.times(8)).get(15);

        Mockito.verify(this.displayBuffer, Mockito.times(1)).write(0, 0, 1);
        Mockito.verify(this.displayBuffer, Mockito.times(1)).write(1, 0, 1);
        Mockito.verify(this.displayBuffer, Mockito.times(1)).write(2, 0, 1);
        Mockito.verify(this.displayBuffer, Mockito.times(1)).write(3, 0, 1);
        Mockito.verify(this.displayBuffer, Mockito.times(1)).write(4, 0, 1);
        Mockito.verify(this.displayBuffer, Mockito.times(1)).write(5, 0, 1);
        Mockito.verify(this.displayBuffer, Mockito.times(1)).write(6, 0, 1);
        Mockito.verify(this.displayBuffer, Mockito.times(1)).write(7, 0, 0);

        Mockito.verifyNoMoreInteractions(this.vRegister, this.displayBuffer);
    }

}
