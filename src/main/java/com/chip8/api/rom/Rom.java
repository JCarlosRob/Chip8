package com.chip8.api.rom;

public interface Rom {

    Byte[] get();

    void set(Byte[] data);

}
