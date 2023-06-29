package com.example.boot.pojo.entity;

import lombok.Data;

@Data
public class FeedBack {
    Integer id;
    Integer userId;
    String content;
    String publishDate;

    @Override
    public String toString() {
        return "FeedBack{" +
                "id=" + id +
                ", userId=" + userId +
                ", content='" + content + '\'' +
                ", publishDate='" + publishDate + '\'' +
                '}';
    }
}
