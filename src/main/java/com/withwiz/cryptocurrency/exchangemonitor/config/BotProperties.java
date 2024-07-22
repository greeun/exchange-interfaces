package com.withwiz.cryptocurrency.exchangemonitor.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("telegram.api")
public class BotProperties extends com.withwiz.sandbeach.interfaces.telegram.config.BotProperties {
}
