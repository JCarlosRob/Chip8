package com.chip8.api.core;

import java.io.IOException;

public interface Loader {

    void pathRom(String path) throws IOException;

    void loadSprites();

}
