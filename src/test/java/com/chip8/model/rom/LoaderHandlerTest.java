package com.chip8.model.rom;

import com.chip8.api.rom.Rom;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

@ExtendWith(MockitoExtension.class)
class LoaderHandlerTest {

    @Mock
    private Rom rom;

    @InjectMocks
    private LoaderHandler loaderRomHandler;

    @Test
    void load_test() throws IOException {
        this.loaderRomHandler.load("src/test/resources/roms/testFile");
        final Byte[] expected = {116, 101, 115, 116};
        Mockito.verify(this.rom, Mockito.times(1)).set(expected);
    }

    @Test
    void load_inputNull_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.loaderRomHandler.load(null));
    }

    @Test
    void load_inputEmpty_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.loaderRomHandler.load(""));
    }

    @Test
    void load_inputPathNotExist_returnException_test() {
        Assertions.assertThrows(IOException.class, () -> this.loaderRomHandler.load("/"));
    }

}
