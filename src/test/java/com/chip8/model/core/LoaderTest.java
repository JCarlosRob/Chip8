package com.chip8.model.core;

import com.chip8.api.core.memory.Memory;
import com.chip8.model.core.sprite.SpritesEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.nio.file.NoSuchFileException;

@ExtendWith(MockitoExtension.class)
class LoaderTest {

    @Mock
    private Memory memoryRam;

    @InjectMocks
    private LoaderHandler loader;

    @Test
    void write_inputData_setDataInToMemoryRam_test() throws IOException {
        this.loader.pathRom("src/test/resources/roms/testFile");

        final Integer[] expected = new Integer[]{116, 101, 115, 116};
        Mockito.verify(this.memoryRam, Mockito.times(1)).write(512, expected);
        Mockito.verifyNoMoreInteractions(this.memoryRam);
    }

    @Test
    void write_fileNotFound_returnException_test() {
        Assertions.assertThrows(NoSuchFileException.class, () -> this.loader.pathRom("src/test/resources/roms/not_found"));
    }

    @Test
    void write_inputEmpty_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.loader.pathRom(""));
    }

    @Test
    void write_inputNull_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.loader.pathRom(null));
    }

    @Test
    void loadSprites_test() {
        this.loader.loadSprites();

        Mockito.verify(this.memoryRam, Mockito.times(1)).write(SpritesEnum.SPRITE_0.getPositionInMemory(), SpritesEnum.SPRITE_0.getSprite());
        Mockito.verify(this.memoryRam, Mockito.times(1)).write(SpritesEnum.SPRITE_1.getPositionInMemory(), SpritesEnum.SPRITE_1.getSprite());
        Mockito.verify(this.memoryRam, Mockito.times(1)).write(SpritesEnum.SPRITE_2.getPositionInMemory(), SpritesEnum.SPRITE_2.getSprite());
        Mockito.verify(this.memoryRam, Mockito.times(1)).write(SpritesEnum.SPRITE_3.getPositionInMemory(), SpritesEnum.SPRITE_3.getSprite());
        Mockito.verify(this.memoryRam, Mockito.times(1)).write(SpritesEnum.SPRITE_4.getPositionInMemory(), SpritesEnum.SPRITE_4.getSprite());
        Mockito.verify(this.memoryRam, Mockito.times(1)).write(SpritesEnum.SPRITE_5.getPositionInMemory(), SpritesEnum.SPRITE_5.getSprite());
        Mockito.verify(this.memoryRam, Mockito.times(1)).write(SpritesEnum.SPRITE_6.getPositionInMemory(), SpritesEnum.SPRITE_6.getSprite());
        Mockito.verify(this.memoryRam, Mockito.times(1)).write(SpritesEnum.SPRITE_7.getPositionInMemory(), SpritesEnum.SPRITE_7.getSprite());
        Mockito.verify(this.memoryRam, Mockito.times(1)).write(SpritesEnum.SPRITE_8.getPositionInMemory(), SpritesEnum.SPRITE_8.getSprite());
        Mockito.verify(this.memoryRam, Mockito.times(1)).write(SpritesEnum.SPRITE_9.getPositionInMemory(), SpritesEnum.SPRITE_9.getSprite());
        Mockito.verify(this.memoryRam, Mockito.times(1)).write(SpritesEnum.SPRITE_A.getPositionInMemory(), SpritesEnum.SPRITE_A.getSprite());
        Mockito.verify(this.memoryRam, Mockito.times(1)).write(SpritesEnum.SPRITE_B.getPositionInMemory(), SpritesEnum.SPRITE_B.getSprite());
        Mockito.verify(this.memoryRam, Mockito.times(1)).write(SpritesEnum.SPRITE_C.getPositionInMemory(), SpritesEnum.SPRITE_C.getSprite());
        Mockito.verify(this.memoryRam, Mockito.times(1)).write(SpritesEnum.SPRITE_D.getPositionInMemory(), SpritesEnum.SPRITE_D.getSprite());
        Mockito.verify(this.memoryRam, Mockito.times(1)).write(SpritesEnum.SPRITE_E.getPositionInMemory(), SpritesEnum.SPRITE_E.getSprite());
        Mockito.verify(this.memoryRam, Mockito.times(1)).write(SpritesEnum.SPRITE_F.getPositionInMemory(), SpritesEnum.SPRITE_F.getSprite());
        Mockito.verifyNoMoreInteractions(this.memoryRam);
    }

}
