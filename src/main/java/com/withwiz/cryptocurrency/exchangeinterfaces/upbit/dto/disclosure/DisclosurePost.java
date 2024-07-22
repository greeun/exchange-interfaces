package com.withwiz.cryptocurrency.exchangeinterfaces.upbit.dto.disclosure;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * DTO: DisclosurePost
 */
@Setter
@Getter
@ToString
public class DisclosurePost {

    /**
     * assets key
     */
    @JsonProperty("assets")
    String assets;

    /**
     * id
     */
    @JsonProperty("id")
    int id;

    /**
     * start date
     */
    @JsonProperty("start_date")
    String startDate;

    /**
     * text
     */
    @JsonProperty("text")
    String text;

    /**
     * url
     */
    @JsonProperty("url")
    String url;
}

