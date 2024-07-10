package com.chip8.model.core.register;

import com.chip8.api.core.register.StackPointer;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class StackPointerHandler implements StackPointer {

    private Integer sp = 0;

    @Override
    public void decrement() {
        Assert.isTrue(this.sp > 0, "The stack pointer can not be decrement");
        this.sp = this.sp - 1;
    }

    @Override
    public void increase() {
        Assert.isTrue(this.sp < 11, "The stack pointer can not be increase");
        this.sp = this.sp + 1;
    }

    @Override
    public Integer get() {
        return this.sp;
    }

    @Override
    public void set(final Integer data) {
        Assert.notNull(data, "The data can not be null");
        this.sp = data;
    }

}
