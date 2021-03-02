package com.withwiz.interfaces.telegram.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * dto: send result
 */
@Setter
@Getter
@ToString
public class SendResult {
    @JsonProperty("message_id")
    long messageId;

    @JsonProperty("from")
    From from;

    @JsonProperty("chat")
    Chat chat;

    @JsonProperty("date")
    long date;

    @JsonProperty("text")
    String text;
}
