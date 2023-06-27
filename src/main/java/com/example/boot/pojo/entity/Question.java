package com.example.boot.pojo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Question {
        @JsonProperty("question_id")
        private  int questionId;

        private String title;
        private String content;
        private String time;

        @JsonProperty("like_amount")
        private int likeAmount;

        @JsonProperty("browser_amount")
        private int browserAmount;

        private boolean state;



}
