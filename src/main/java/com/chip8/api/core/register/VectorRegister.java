package com.chip8.api.core.register;

public interface VectorRegister {

    void setVRegister(Integer position, Integer data);

    Integer getVRegister(Integer position);

}
