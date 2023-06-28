package com.example.boot.pojo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Article {

    Integer id;

    String title;

    String author;

    @JsonProperty("like_amount")
    Integer likeAmount;

    Integer starAmount;

    Integer browserAmount;

    String publishDate;
}
