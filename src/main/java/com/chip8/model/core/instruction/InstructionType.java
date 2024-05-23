package com.chip8.model.core.instruction;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public enum InstructionType {

    SYS_CALL,
    CLS,
    RETURN,
    JUMP_ADDR,
    CALL_ADDR,
    SKIP_EQUALS_BYTE,
    SKIP_NOT_EQUALS_BYTE,
    SKIP_EQUALS_REGISTER,
    LOAD_BYTE,
    ADD_BYTE,
    LOAD_REGISTER,
    OR,
    AND,
    XOR,
    ADD_REGISTER,
    SUBTRACT_REGISTER,
    SHR,
    SUBN,
    SHL,
    SKIP_NOT_EQUALS_REGISTER;

}
