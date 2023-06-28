package com.example.boot.pojo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Question {

        private  int id;

        private  Integer userId;
        private String title;
        private String content;
        private String time;

        @JsonProperty("like_amount")
        private int likeAmount;

        @JsonProperty("browser_amount")
        private int browserAmount;

        private boolean state;

        Integer hot;

        @Override
        public String toString() {
                return "Question{" +
                        "questionId=" + id +
                        ", userId=" + userId +
                        ", title='" + title + '\'' +
                        ", content='" + content + '\'' +
                        ", time='" + time + '\'' +
                        ", likeAmount=" + likeAmount +
                        ", browserAmount=" + browserAmount +
                        ", state=" + state +
                        ", hot=" + hot +
                        '}';
        }
}
