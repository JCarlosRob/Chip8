package com.chip8.model.keyboard;

import com.chip8.api.keyboard.Keyboard;
import com.chip8.configure.KeyboardConfigure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class KeyboardHandler implements Keyboard {

    @Autowired
    private KeyboardConfigure keyboardConfigure;

    @Override
    public String read() {
        String input = "";
        try (final Scanner scanner = new Scanner(System.in)) {
            do {
                input = scanner.nextLine();
            } while (!this.keyboardConfigure.getKeys().contains(input.toUpperCase().trim()));
        }
        return input.toUpperCase().trim();
    }

}
