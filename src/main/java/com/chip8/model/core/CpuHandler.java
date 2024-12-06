package com.chip8.model.core;

import com.chip8.api.core.Cpu;
import com.chip8.api.core.buffer.Buffer;
import com.chip8.api.core.instruction.Instruction;
import com.chip8.api.core.memory.Memory;
import com.chip8.api.core.register.IndexRegister;
import com.chip8.api.core.register.ProgramCounter;
import com.chip8.api.core.register.TimerRegister;
import com.chip8.api.core.register.VRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.HexFormat;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class CpuHandler implements Cpu {

    private final List<Instruction> instructions;

    private final ProgramCounter pc;

    private final Memory memoryRam;

    @Autowired
    private Buffer displayBuffer;

    @Autowired
    private IndexRegister indexRegister;

    @Autowired
    private VRegister vRegister;

    @Autowired
    private TimerRegister delayTimerRegister;

    public CpuHandler(final List<Instruction> instructions, final ProgramCounter pc, final Memory memoryRam) {
        this.instructions = instructions;
        this.pc = pc;
        this.memoryRam = memoryRam;
    }

    @Override
    public void run() throws IOException, InterruptedException {
        while (true) {
            final String opcode = this.nextOpcode();
            this.instructions.stream().filter(instruction -> instruction.isExecutable(opcode))
                    .findFirst()
                    .ifPresent(instruction -> instruction.run(opcode));

            if (this.delayTimerRegister.get() > 0) {
                this.delayTimerRegister.set(0);
            }

            //Thread.sleep(16);
        }
    }

    private String nextOpcode() {
        final Integer pc = this.pc.get();
        this.pc.next();
        return Arrays.stream(this.memoryRam.read(pc, pc + 1))
                .map(integer -> HexFormat.of().toHexDigits(integer))
                .map(s -> s.substring(s.length() - 2).toUpperCase())
                .collect(Collectors.joining());
    }
}
