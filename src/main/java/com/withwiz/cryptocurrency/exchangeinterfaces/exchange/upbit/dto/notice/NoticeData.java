package com.withwiz.cryptocurrency.exchangeinterfaces.exchange.upbit.dto.notice;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.withwiz.cryptocurrency.exchangeinterfaces.exchange.upbit.dto.disclosure.DisclosurePost;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * DTO: NoticeData
 */
@Setter
@Getter
@ToString
public class NoticeData {
    /**
     * total count
     */
    @JsonProperty("total_count")
    int totalCount;

    /**
     * total pages
     */
    @JsonProperty("totalPages")
    int totalPages;

    /**
     * post list
     */
    @JsonProperty("list")
    List<NoticePost> noticePosts;

    /**
     * fixed post list
     */
    @JsonProperty("fixed_notices")
    List<NoticePost> fixedNoticePosts;
}
