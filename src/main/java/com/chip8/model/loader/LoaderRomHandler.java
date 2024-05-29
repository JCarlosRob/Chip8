package com.chip8.model.loader;

import com.chip8.api.loader.LoaderRom;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class LoaderRomHandler implements LoaderRom {


    @Override
    public Byte[] load(final String path) throws IOException {
        final Path pathRom = Paths.get("src/main/resources/roms/PONG");
        final byte[] bytes = Files.readAllBytes(pathRom);

        return new Byte[]{};
    }
}
