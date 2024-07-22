package com.withwiz.cryptocurrency.exchangeinterfaces.upbit.dto.disclosure;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * DTO: DisclosureData
 */
@Setter
@Getter
@ToString
public class DisclosureData {
    /**
     * more
     */
    @JsonProperty("more")
    boolean more;

    /**
     * offset
     */
    @JsonProperty("offset")
    int offset;

    /**
     * post list
     */
    @JsonProperty("posts")
    List<DisclosurePost> posts;
}
