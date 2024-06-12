package com.chip8.configure;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "keyboard")
@Getter
@Setter
public class KeyboardConfigure {

    private List<String> keys;

}
