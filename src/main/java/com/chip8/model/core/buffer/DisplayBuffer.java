package com.chip8.model.core.buffer;

import com.chip8.api.core.buffer.Buffer;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.stream.IntStream;

@Component
public class DisplayBuffer implements Buffer {

    private static final Integer POSITION_MAX_X = 64;
    private static final Integer POSITION_MAX_Y = 32;

    private Integer[][] display;

    @PostConstruct
    private void init() {
        this.display = IntStream.range(0, POSITION_MAX_X)
                .mapToObj(r -> IntStream.range(0, POSITION_MAX_Y)
                        .mapToObj(c -> 0)
                        .toArray(Integer[]::new))
                .toArray(Integer[][]::new);
    }

    @Override
    public Integer read(final Integer x, final Integer y) {
//        Assert.notNull(x, "The X position can not be null");
//        Assert.isTrue(x >= 0, "The X position can not be negative");
//        Assert.isTrue(x < POSITION_MAX_X, "The X position can not be greater than " + POSITION_MAX_X);
//        Assert.notNull(y, "The Y position can not be null");
//        Assert.isTrue(y >= 0, "The Y position can not be negative");
//        Assert.isTrue(y < POSITION_MAX_Y, "The Y position can not be greater than " + POSITION_MAX_Y);
        return this.display[x % POSITION_MAX_X][y % POSITION_MAX_Y];
    }

    @Override
    public Integer read(final Integer x) {
        return this.read(x % POSITION_MAX_X, x / POSITION_MAX_X);
    }

    @Override
    public void write(final Integer x, final Integer y, final Integer data) {
//        Assert.notNull(x, "The X position can not be null");
//        Assert.isTrue(x >= 0, "The X position can not be negative");
//        Assert.isTrue(x < POSITION_MAX_X, "The X position can not be greater than " + POSITION_MAX_X);
//        Assert.notNull(y, "The Y position can not be null");
//        Assert.isTrue(y >= 0, "The Y position can not be negative");
//        Assert.isTrue(y < POSITION_MAX_Y, "The Y position can not be greater than " + POSITION_MAX_Y);
//        Assert.notNull(data, "The data parameter can not be null");
        this.display[x % POSITION_MAX_X][y % POSITION_MAX_Y] = data;
    }

    @Override
    public void write(final Integer x, final Integer y, final Integer[] data) {
        IntStream.range(0, data.length).forEach(i -> {
            this.write((x + i) % POSITION_MAX_X, y % POSITION_MAX_Y, data[i]);
        });
    }

    @Override
    public Integer[][] get() {
        return this.display;
    }

    @Override
    public void reset() {
        this.init();
    }
}
