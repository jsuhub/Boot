package com.example.boot.pojo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Comment {
    Integer id;

    @JsonProperty("com_Content")
    String comContent;

    @JsonProperty("com_Like")
    Integer comLike;

    @JsonProperty("user_id")
    Integer userId;

    @JsonProperty("pro_id")
    Integer proId;

    @JsonProperty("essay_id")
    String content;

    Integer likeAmount;

    @JsonProperty("user_id")
    Integer userId;

    @JsonProperty("pro_id")
    Integer proId;

    @JsonProperty("essay_id")
  
    Integer essayId;

    String date;

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", comLike=" + likeAmount
                +
                ", essayId=" + essayId +
                ", date='" + date + '\'' +
                '}';
    }
}
