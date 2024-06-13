package com.chip8.model.core.instruction;

import com.chip8.api.core.register.IndexRegister;
import com.chip8.api.core.register.VRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HexFormat;
import java.util.stream.IntStream;

@Component
public class LoadBCDInstruction extends InstructionAbstract {

    private static final String COMMAND_REGEX = "^F\\w{1}33$";

    private final VRegister vRegister;

    private final IndexRegister indexRegister;

    @Autowired
    public LoadBCDInstruction(final VRegister vRegister, final IndexRegister indexRegister) {
        super(COMMAND_REGEX);
        this.vRegister = vRegister;
        this.indexRegister = indexRegister;
    }

    @Override
    public void execute(final String data) {
        final Integer vData = this.vRegister.get(HexFormat.fromHexDigits(data.substring(1, 2)));
        final String vDataString = this.normalize(String.valueOf(vData));

        IntStream.range(0, 3).forEach(i -> {
            final String digit = vDataString.substring(i, i + 1);
            this.vRegister.set(Integer.valueOf(digit), this.indexRegister.get() + i);
        });
    }

    private String normalize(final String vDataString) {
        String dataNormalized = vDataString;
        if (dataNormalized.length() < 3) {
            dataNormalized = "00" + dataNormalized;
        }
        return dataNormalized.substring(dataNormalized.length() - 3);
    }
}