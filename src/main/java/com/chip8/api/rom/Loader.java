package com.chip8.api.rom;

import java.io.IOException;

public interface Loader {

    void load(String path) throws IOException;

}
