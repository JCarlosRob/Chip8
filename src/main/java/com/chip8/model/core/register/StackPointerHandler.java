package com.chip8.model.core.register;

import com.chip8.api.core.register.StackPointer;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class StackPointerHandler implements StackPointer {

    private Integer sp;

    @Override
    public void decrement() {
        if (Objects.nonNull(this.sp) && this.sp > 0) {
            this.sp = this.sp - 1;
        }
    }

    @Override
    public void increase() {
        if (Objects.nonNull(this.sp) && this.sp < 11) {
            this.sp = this.sp + 1;
        }
    }

    @Override
    public Integer get() {
        return this.sp;
    }

    @Override
    public void set(final Integer data) {
        this.sp = data;
    }

}
