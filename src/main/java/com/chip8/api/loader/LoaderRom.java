package com.chip8.api.loader;

import java.io.IOException;

public interface LoaderRom {

    Byte[] load(String path) throws IOException;

}
