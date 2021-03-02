package com.withwiz.interfaces.telegram.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * response for "send message"
 */
@Setter
@Getter
@ToString
public class TelegramResponseSendMessage {
    @JsonProperty("ok")
    boolean ok;

    @JsonIgnore
    @JsonProperty("result")
    SendResult result;

//    @JsonIgnore
//    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @JsonProperty("error_code")
    int errorCode;

    @JsonIgnore
    @JsonProperty("description")
    String description;
}
