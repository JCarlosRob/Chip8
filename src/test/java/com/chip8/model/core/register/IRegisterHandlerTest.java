package com.chip8.model.core.register;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class IRegisterHandlerTest {

    private final IRegisterHandler viRegisterHandler = new IRegisterHandler();

    @Test
    void getViRegister_test() {
        this.viRegisterHandler.setVI(Short.parseShort("1"));
        Assertions.assertEquals(Short.parseShort("1"), this.viRegisterHandler.getVI());
    }

}
