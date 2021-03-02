package com.withwiz.interfaces.telegram.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * From object
 */
@Setter
@Getter
@ToString
public class From {
    /**
     * id
     */
    @JsonProperty("id")
    long id;

    /**
     * is_bot
     */
    @JsonProperty("is_bot")
    boolean isBot;

    /**
     * first name
     */
    @JsonProperty("first_name")
    String firstName;

    /**
     * user name
     */
    @JsonProperty("username")
    String userName;
}
