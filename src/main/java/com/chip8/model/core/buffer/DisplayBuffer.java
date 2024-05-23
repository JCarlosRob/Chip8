package com.chip8.model.core.buffer;

import com.chip8.api.core.buffer.Buffer;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import java.util.stream.IntStream;

@Component
public class DisplayBuffer implements Buffer {

    private final static Integer POSITION_MAX_X = 64;
    private final static Integer POSITION_MAX_Y = 32;

    private Byte[][] display;

    @PostConstruct
    private void init() {
        display = IntStream.range(0, POSITION_MAX_Y)
                .mapToObj(r -> IntStream.range(0, POSITION_MAX_X)
                        .mapToObj(c -> (byte) 0)
                        .toArray(Byte[]::new))
                .toArray(Byte[][]::new);
    }

    @Override
    public Byte read(final Short x, final Short y) {
        Assert.notNull(x, "The X position can not be null");
        Assert.isTrue(x >= 0, "The X position can not be negative");
        Assert.isTrue(x < POSITION_MAX_X, "The X position can not be greater than " + POSITION_MAX_X);
        Assert.notNull(y, "The Y position can not be null");
        Assert.isTrue(y >= 0, "The Y position can not be negative");
        Assert.isTrue(y < POSITION_MAX_Y, "The Y position can not be greater than " + POSITION_MAX_Y);
        return display[x][y];
    }

    @Override
    public void write(final Short x, Short y, final Byte data) {
        Assert.notNull(x, "The X position can not be null");
        Assert.isTrue(x >= 0, "The X position can not be negative");
        Assert.isTrue(x < POSITION_MAX_X, "The X position can not be greater than " + POSITION_MAX_X);
        Assert.notNull(y, "The Y position can not be null");
        Assert.isTrue(y >= 0, "The Y position can not be negative");
        Assert.isTrue(y < POSITION_MAX_Y, "The Y position can not be greater than " + POSITION_MAX_Y);
        Assert.notNull(data, "The data parameter can not be null");
        display[x][y] = data;
    }

    @Override
    public Byte[][] get() {
        return display;
    }

    @Override
    public void reset() {
        init();
    }
}
