package com.example.boot.mapper;

import com.example.boot.pojo.entity.Question;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestQuestionMapper {
    @Autowired
    QuestionMapper questionMapper;

    @Test
    void test(){
        Question question = new Question();
        question.setTitle("相比于mybaits，用mybaits_plus的优势");
        System.out.println(question);
        questionMapper.insert(question);
    }
}
