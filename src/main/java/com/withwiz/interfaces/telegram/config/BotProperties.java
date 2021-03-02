package com.withwiz.interfaces.telegram.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Setter
@Getter
@ToString
@Component
@ConfigurationProperties("telegram.api")
public class BotProperties {
    List<Bot> bots;
}
