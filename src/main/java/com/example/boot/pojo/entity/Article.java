package com.example.boot.pojo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.http.converter.json.GsonBuilderUtils;

@Data
public class Article {

    Integer id;

    String title;

    String author;

    String content;

    Integer hot;
  
    @JsonProperty("like_amount")
    Integer likeAmount;

    @JsonProperty("star_amount")
    Integer starAmount;
  
    @JsonProperty("browser_amount")
    Integer browserAmount;
  
    String description;

    String tag;

    @JsonProperty("publish_date")
    String publishDate;

    @JsonProperty("comment_amount")
    Integer commentAmount;

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", content='" + content + '\'' +
                ", hot=" + hot +
                ", likeAmount=" + likeAmount +
                ", starAmount=" + starAmount +
                ", browserAmount=" + browserAmount +
                ", description='" + description + '\'' +
                ", tag='" + tag + '\'' +
                ", commentAmount=" + commentAmount +
                '}';
    }

}
