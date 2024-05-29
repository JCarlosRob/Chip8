package com.chip8.api.core.buffer;

public interface Buffer {

    Integer read(Integer x, Integer y);

    void write(Integer x, Integer y, Integer data);

    Integer[][] get();

    void reset();

}
