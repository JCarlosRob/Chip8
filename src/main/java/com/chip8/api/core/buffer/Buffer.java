package com.chip8.api.core.buffer;

public interface Buffer {

    Byte read(Short x, Short y);

    void write(Short x, Short y, Byte data);

    Byte[][] get();

    void reset();

}
