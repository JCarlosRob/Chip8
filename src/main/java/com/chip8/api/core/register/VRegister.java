package com.chip8.api.core.register;

public interface VRegister {

    void set(Integer position, Integer data);

    Integer get(Integer position);

    void add(Integer position, Integer data);

    void reset();

}
