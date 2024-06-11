package com.chip8.model.loader;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class LoaderRomHandlerTest {

    private LoaderRomHandler loaderRomHandler;

    @BeforeEach
    void setUp() {
        this.loaderRomHandler = new LoaderRomHandler();
    }

    @Test
    void load_test() throws IOException {
        final Byte[] result = this.loaderRomHandler.load("src/test/resources/roms/testFile");
        final Byte[] expected = {116, 101, 115, 116};
        Assertions.assertArrayEquals(expected, result);
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
