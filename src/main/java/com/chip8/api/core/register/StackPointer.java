package com.chip8.api.core.register;

public interface StackPointer {

    void decrement();

    void increase();

    Integer get();

    void set(Integer data);

}
