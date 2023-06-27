package com.example.boot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.boot.mapper.QuestionMapper;
import com.example.boot.pojo.entity.Question;
import com.example.boot.service.IQuestionService;
import org.springframework.stereotype.Service;

@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question> implements IQuestionService {

}
