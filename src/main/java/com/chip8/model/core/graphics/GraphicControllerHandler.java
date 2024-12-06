package com.chip8.model.core.graphics;

import com.chip8.api.core.buffer.Buffer;
import com.chip8.api.core.graphics.GraphicController;
import com.chip8.api.core.register.VRegister;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.io.IOException;
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
        Assert.notNull(x, "The x position can not be null");
        Assert.notNull(y, "The y position can not be null");
        Assert.notNull(data, "The data position can not be null");

        this.restoreCollision();
        final String[] sprite = this.calculateSpriteToBeDisplayed(data);
        this.loadSprite(x, y, sprite);
        try {
            this.print();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void restoreCollision() {
        this.vRegister.set(15, 0);
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

    private void calculateCollision(final Integer byteSprite, final Integer byteDisplay) {
        if (this.vRegister.get(15) == 0 && byteSprite == 1 && byteDisplay == 1) {
            this.vRegister.set(15, 1);
        }
    }

    private void loadSpriteSection(final Integer x, final Integer y, final Integer[] spriteSection) {
        IntStream.range(0, spriteSection.length).forEach(i -> {
            final Integer displayValue = this.displayBuffer.read(x + i, y);
            this.calculateCollision(displayValue, displayValue);
            this.displayBuffer.write(x + i, y, spriteSection[i] ^ displayValue);
        });


    }

    private String spriteSectionNormalizer(final String spriteSection) {
        final String zeros = IntStream.range(0, 8 - spriteSection.length()).mapToObj(value -> "0").collect(Collectors.joining());
        return zeros + spriteSection;
    }

    private void print() throws IOException {
        //Runtime.getRuntime().exec("cls");
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("   0         1         2         3         4         5         6   ");
        System.out.println("   0123456789012345678901234567890123456789012345678901234567890123");
        System.out.println("   ################################################################");
        IntStream.range(0, this.displayBuffer.get()[0].length).forEach(y -> {
            if (y<10) {
                System.out.print("0" + y + "#");
            } else {
                System.out.print(y + "#");
            }

            IntStream.range(0, this.displayBuffer.get().length).forEach(x ->
                    System.out.print(String.valueOf(this.displayBuffer.get()[x][y]).replace("0", " ")));
            System.out.print("#");
            System.out.println();
        });
        System.out.println("   ################################################################");

//            Arrays.stream(this.displayBuffer.get()).forEach(integers -> System.out.println(Arrays.toString(integers)));
        System.out.println();
        System.out.println();
    }

}
