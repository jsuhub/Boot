package com.example.boot.pojo.entity;

import lombok.Data;

@Data
public class Response {
    private Integer id;
    private String content;
    private String time;
    private int like_amount;
    private int browse_amount;
    private int question_id;




}
