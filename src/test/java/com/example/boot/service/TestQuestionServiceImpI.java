package com.example.boot.service;

import com.example.boot.service.impl.QuestionServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestQuestionServiceImpI {
    @Autowired
    QuestionServiceImpl questionService;

    @Test
    void testQuestionByFollow(){
        System.out.println(questionService.showProblem(1));

    }

}
