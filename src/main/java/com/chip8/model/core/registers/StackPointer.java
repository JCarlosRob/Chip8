package com.chip8.model.core.registers;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@Getter
@Setter
public class StackPointer {

    private Short sp;

    public void decrement() {
        if (Objects.nonNull(this.sp) && this.sp > 0) {
            this.sp = (short) (this.sp - 1);
        }
    }

    public void increase() {
        if (Objects.nonNull(this.sp) && this.sp < 11) {
            this.sp = (short) (this.sp + 1);
        }
    }

}
