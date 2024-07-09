package com.chip8.model.rom;

import com.chip8.api.core.memory.Memory;
import com.chip8.api.rom.Rom;
import com.chip8.model.core.LoaderHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

@ExtendWith(MockitoExtension.class)
class LoaderHandlerTest {

    @Mock
    private Rom rom;

    @Mock
    private Memory memoryRam;

    @InjectMocks
    private LoaderHandler loaderRomHandler;

    @Test
    void pathRom_test() throws IOException {
        this.loaderRomHandler.pathRom("src/test/resources/roms/testFile");
        final Byte[] expected = {116, 101, 115, 116};
        //Mockito.verify(this.rom, Mockito.times(1)).set(expected);
    }

    @Test
    void pathRom_inputNull_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.loaderRomHandler.pathRom(null));
    }

    @Test
    void pathRom_inputEmpty_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.loaderRomHandler.pathRom(""));
    }

    @Test
    void pathRom_inputPathNotExist_returnException_test() {
        Assertions.assertThrows(IOException.class, () -> this.loaderRomHandler.pathRom("/"));
    }

}
