package com.chip8.model.core.register;

import com.chip8.api.core.register.VRegister;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import java.util.stream.IntStream;

@Component
public class VRegisterHandler implements VRegister {

    private static final Integer MAX_LENGTH_V_REGISTER = 16;

    private Integer[] v = new Integer[MAX_LENGTH_V_REGISTER];

    @PostConstruct
    private void init() {
        this.v = IntStream.range(0, MAX_LENGTH_V_REGISTER)
                .mapToObj(c -> 0)
                .toArray(Integer[]::new);
    }

    @Override
    public void set(final Integer position, final Integer data) {
        Assert.notNull(position, "The position can not be null");
        Assert.isTrue(position >= 0, "The position can not be negative");
        Assert.isTrue(position < this.v.length, "The position can not be greater than " + (this.v.length - 1));
        this.v[position] = data;
    }

    @Override
    public Integer get(final Integer position) {
        Assert.notNull(position, "The position can not be null");
        Assert.isTrue(position >= 0, "The position can not be negative");
        Assert.isTrue(position < this.v.length, "The position can not be greater than " + (this.v.length - 1));
        return this.v[position];
    }

    @Override
    public void add(final Integer position, final Integer data) {
        this.v[position] = this.v[position] + data;
    }

}
