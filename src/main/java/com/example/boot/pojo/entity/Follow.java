package com.example.boot.pojo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
    public class Follow
    {
        Integer id;

        @JsonProperty("user_id")
        Integer userId;
        Integer user1Id;
        String date;
    }
