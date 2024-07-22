package com.withwiz.cryptocurrency.exchangeinterfaces.upbit.dto.disclosure;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * DTO: ProjectDisclosure
 */
@Setter
@Getter
@ToString
public class ProjectDisclosure {

    /**
     * data
     */
    @JsonProperty("data")
    DisclosureData data;

    /**
     * success
     */
    @JsonProperty("success")
    boolean success;

    /**
     * time
     */
    @JsonProperty("time")
    String time;
}


