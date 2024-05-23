package com.chip8.api.core.registers;

public interface Registers {

    void setVRegister(Integer position, Byte data);

    Byte getVRegister(Integer position);

    void setIRegister(Short data);

    Short getIRegister();

}
