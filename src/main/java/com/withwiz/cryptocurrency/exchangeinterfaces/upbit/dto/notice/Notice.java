package com.withwiz.cryptocurrency.exchangeinterfaces.upbit.dto.notice;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * DTO: CoinListing in Notce
 */
@Setter
@Getter
@ToString
public class Notice {
    /**
     * data
     */
    @JsonProperty("data")
    NoticeData data;

    /**
     * success
     */
    @JsonProperty("success")
    boolean success;
}
