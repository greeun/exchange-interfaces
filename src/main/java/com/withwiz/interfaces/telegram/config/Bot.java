package com.withwiz.interfaces.telegram.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Bot {
    String name;

    String token;

    long chatId;
}
