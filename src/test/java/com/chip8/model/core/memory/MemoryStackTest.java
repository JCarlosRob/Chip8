package com.chip8.model.core.memory;

import com.chip8.api.core.memory.Memory;
import com.chip8.api.core.register.StackPointer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class MemoryStackTest {

    @Mock
    private StackPointer sp;

    @Mock
    private Memory memoryStack;

    @InjectMocks
    private MemoryStackHandler memoryStackHandler;

    @Test
    void push_test() {
        Mockito.when(this.sp.get()).thenReturn(2);
        this.memoryStackHandler.push(1);

        final InOrder inOrder = Mockito.inOrder(this.sp, this.memoryStack);
        inOrder.verify(this.sp, Mockito.times(1)).increase();
        inOrder.verify(this.memoryStack, Mockito.times(1)).write(2, 1);
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    void pop_test() {
        Mockito.when(this.sp.get()).thenReturn(1);
        this.memoryStackHandler.pop();

        final InOrder inOrder = Mockito.inOrder(this.sp, this.memoryStack);
        inOrder.verify(this.memoryStack, Mockito.times(1)).read(1);
        inOrder.verify(this.sp, Mockito.times(1)).decrement();
        inOrder.verifyNoMoreInteractions();
    }

}
