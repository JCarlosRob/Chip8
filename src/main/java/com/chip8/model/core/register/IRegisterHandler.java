package com.chip8.model.core.register;

import com.chip8.api.core.register.IRegister;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class IRegisterHandler implements IRegister {

    private Short vI;

}
