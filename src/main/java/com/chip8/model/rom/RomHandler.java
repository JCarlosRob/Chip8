package com.chip8.model.rom;

import com.chip8.api.rom.Rom;
import org.springframework.stereotype.Component;

@Component
public class RomHandler implements Rom {

    private Byte[] rom;

    @Override
    public Byte[] get() {
        return this.rom;
    }

    @Override
    public void set(final Byte[] data) {
        this.rom = data;
    }
}
