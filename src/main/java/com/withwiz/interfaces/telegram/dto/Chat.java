package com.withwiz.interfaces.telegram.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Chat object
 */
@Setter
@Getter
@ToString
public class Chat {
    @JsonProperty("id")
    long id;

    @JsonProperty("first_name")
    String first_name;

    @JsonProperty("last_name")
    String lastName;

    @JsonProperty("username")
    String userName;

    @JsonProperty("type")
    String type;
}
