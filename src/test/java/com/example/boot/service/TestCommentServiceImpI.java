package com.example.boot.service;

import com.example.boot.mapper.CommentMapper;
import com.example.boot.pojo.entity.Comment;
import com.example.boot.service.impl.CommentServicelmpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestCommentServiceImpI {
    @Autowired
    CommentServicelmpl commentServicelmpl;

    @Test
    public void testComment(){
        System.out.println( commentServicelmpl.selectListCommentByArticleId(1));
    }

    @Test
    public  void  test1Comment(){
        System.out.println(commentServicelmpl.selectListCommentByQuestionId(1));
    }
}
