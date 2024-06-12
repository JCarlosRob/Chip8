package com.chip8.model.core.instruction;

import com.chip8.api.core.buffer.Buffer;
import com.chip8.api.core.memory.Memory;
import com.chip8.api.core.register.IndexRegister;
import com.chip8.api.core.register.VRegister;
import org.springframework.stereotype.Component;

import java.util.HexFormat;
import java.util.stream.IntStream;

@Component
public class DrawInstruction extends InstructionAbstract {

    private static final String COMMAND_REGEX = "^D\\w{3}";

    private final VRegister vRegister;

    private final Memory memoryRam;

    private final IndexRegister indexRegister;

    private final Buffer displayBuffer;

    public DrawInstruction(final VRegister vRegister, final Memory memoryRam, final IndexRegister indexRegister,
                           final Buffer displayBuffer) {
        super(COMMAND_REGEX);
        this.vRegister = vRegister;
        this.memoryRam = memoryRam;
        this.indexRegister = indexRegister;
        this.displayBuffer = displayBuffer;
    }

    @Override
    public void execute(final String opcode) {
        final Integer vx = this.vRegister.get(HexFormat.fromHexDigits(opcode.substring(1, 2)));
        final Integer vy = this.vRegister.get(HexFormat.fromHexDigits(opcode.substring(2, 3)));
        final Integer[] dataOfMemory = this.memoryRam.read(this.indexRegister.get(), this.indexRegister.get() + HexFormat.fromHexDigits(opcode.substring(3)));

        this.vRegister.set(15, this.calculateCollision(vx, vy, dataOfMemory) ? 1 : 0);
        this.calculateDataToBeDisplayed(vx, vy, dataOfMemory);
    }

    private Boolean calculateCollision(final Integer vx, final Integer vy, final Integer[] dataOfMemory) {
        return IntStream.range(0, dataOfMemory.length).anyMatch(i -> dataOfMemory[i] == 1 && this.displayBuffer.read((vx + i) & 63, vy) == 1);
    }

    private void calculateDataToBeDisplayed(final Integer vx, final Integer vy, final Integer[] dataOfMemory) {
        IntStream.range(0, dataOfMemory.length)
                .forEach(i -> {
                    final Integer vxCalculated = (vx + i) & 63;
                    final Integer dataDisplay = this.displayBuffer.read(vxCalculated, vy);
                    this.displayBuffer.write(vxCalculated, vy, dataOfMemory[i] ^ dataDisplay);
                });
    }
}
