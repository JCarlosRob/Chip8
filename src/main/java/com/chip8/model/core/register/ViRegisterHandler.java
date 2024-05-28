package com.chip8.model.core.register;

import com.chip8.api.core.register.ViRegister;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class ViRegisterHandler implements ViRegister {

    private Short vI;

}
