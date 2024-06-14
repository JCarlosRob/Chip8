package com.chip8.model.rom;

import com.chip8.api.rom.Loader;
import com.chip8.api.rom.Rom;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.IntStream;

@Component
public class LoaderHandler implements Loader {

    private final Rom rom;

    public LoaderHandler(final Rom rom) {
        this.rom = rom;
    }

    @Override
    public void load(final String path) throws IOException {
        Assert.isTrue(StringUtils.hasLength(path), "The path can not be null or empty");
        final Path pathRom = Paths.get(path);
        final byte[] romBytes = Files.readAllBytes(pathRom);
        this.rom.set(IntStream.range(0, romBytes.length).mapToObj(i -> romBytes[i]).toArray(Byte[]::new));
    }

}
