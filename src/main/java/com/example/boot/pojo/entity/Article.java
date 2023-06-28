package com.example.boot.pojo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Article {

    Integer id;

    String title;

    String author;

    String description;

    String tag;

    String content;

    @JsonProperty("publish_date")
    String publishDate;

    @JsonProperty("like_amount")
    Integer likeAmount;

    @JsonProperty("browser_amount")
    Integer browserAmount;

    @JsonProperty("comment_amount")
    Integer commentAmount;
}
