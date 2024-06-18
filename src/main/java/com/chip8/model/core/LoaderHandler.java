package com.chip8.model.core;

import com.chip8.api.core.Loader;
import com.chip8.api.core.memory.Memory;
import com.chip8.api.rom.Rom;
import com.chip8.model.core.sprite.SpritesEnum;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HexFormat;
import java.util.stream.IntStream;

@Component
public class LoaderHandler implements Loader {

    private final Rom rom;

    private final Memory memoryRam;

    public LoaderHandler(final Rom rom, final Memory memoryRam) {
        this.rom = rom;
        this.memoryRam = memoryRam;
    }

    @Override
    public void pathRom(final String path) throws IOException {
        Assert.isTrue(StringUtils.hasLength(path), "The path can not be null or empty");
        final Path pathRom = Paths.get(path);
        final byte[] romBytes = Files.readAllBytes(pathRom);

        final String a = String.valueOf(IntStream.range(0, romBytes.length).mapToObj(i -> HexFormat.of().toHexDigits(romBytes[i])).toList());

        final Integer[] rom = IntStream.range(0, romBytes.length)
                .mapToObj(i -> HexFormat.of().toHexDigits(romBytes[i]))
                .map(HexFormat::fromHexDigits)
                .toArray(Integer[]::new);

        this.memoryRam.write(512, rom);
    }

    @Override
    public void loadSprites() {
        Arrays.stream(SpritesEnum.values()).forEach(spritesEnum -> this.memoryRam.write(spritesEnum.getPositionInMemory(), spritesEnum.getSprite()));
    }

}
