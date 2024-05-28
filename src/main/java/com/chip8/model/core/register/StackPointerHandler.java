package com.chip8.model.core.register;

import com.chip8.api.core.register.StackPointer;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@Getter
@Setter
public class StackPointerHandler implements StackPointer {

    private Short sp;

    @Override
    public void decrement() {
        if (Objects.nonNull(this.sp) && this.sp > 0) {
            this.sp = (short) (this.sp - 1);
        }
    }

    @Override
    public void increase() {
        if (Objects.nonNull(this.sp) && this.sp < 11) {
            this.sp = (short) (this.sp + 1);
        }
    }

}
