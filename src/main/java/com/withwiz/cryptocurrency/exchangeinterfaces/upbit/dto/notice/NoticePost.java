package com.withwiz.cryptocurrency.exchangeinterfaces.upbit.dto.notice;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * DTO: NoticePost
 */
@Setter
@Getter
@ToString
public class NoticePost {
    /**
     * id
     */
    @JsonProperty("id")
    int id;

    /**
     * title
     */
    @JsonProperty("title")
    String title;

    /**
     * created_at
     */
    @JsonProperty("created_at")
    String createdAt;

    /**
     * updated_at
     */
    @JsonProperty("updated_at")
    String updatedAt;

    /**
     * view_count
     */
    @JsonProperty("view_count")
    int viewCount;
}

