package com.chip8.api.core.register;

public interface VRegister {

    void setVRegister(Integer position, Byte data);

    Byte getVRegister(Integer position);

}
