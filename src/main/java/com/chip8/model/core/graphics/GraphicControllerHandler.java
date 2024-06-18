package com.chip8.model.core.graphics;

import com.chip8.api.core.buffer.Buffer;
import com.chip8.api.core.graphics.GraphicController;
import com.chip8.api.core.register.VRegister;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class GraphicControllerHandler implements GraphicController {

    private final Buffer displayBuffer;

    private final VRegister vRegister;

    public GraphicControllerHandler(final Buffer displayBuffer, final VRegister vRegister) {
        this.displayBuffer = displayBuffer;
        this.vRegister = vRegister;
    }

    @Override
    public void display(final Integer x, final Integer y, final Integer[] data) {
        final String[] sprite = this.calculateSpriteToBeDisplayed(data);
        this.loadSprite(x, y, sprite);
    }

    private Boolean calculateCollision(final Integer vx, final Integer vy, final Integer[] dataOfMemory) {
        //TODO
        return false;
    }

    private String[] calculateSpriteToBeDisplayed(final Integer[] data) {
        return Arrays.stream(data).map(Integer::toBinaryString).map(this::spriteSectionNormalizer).toArray(String[]::new);
    }

    private void loadSprite(final Integer x, final Integer y, final String[] sprite) {
        IntStream.range(0, sprite.length)
                .forEach(i -> {
                    final Integer[] spriteSection = Arrays.stream(sprite[i].split("")).map(Integer::valueOf).toArray(Integer[]::new);
                    this.loadSpriteSection(x, y + i, spriteSection);
                });
    }

    private void loadSpriteSection(final Integer x, final Integer y, final Integer[] spriteSection) {
        IntStream.range(0, spriteSection.length).forEach(i -> {
            final Integer displayValue = this.displayBuffer.read(x + i, y);
            this.displayBuffer.write(x + i, y, spriteSection[i] ^ displayValue);
        });


    }

    private String spriteSectionNormalizer(final String spriteSection) {
        final String zeros = IntStream.range(0, 8 - spriteSection.length()).mapToObj(value -> "0").collect(Collectors.joining());
        return zeros + spriteSection;
    }

}
