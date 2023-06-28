package com.example.boot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.boot.pojo.entity.Article;
import com.example.boot.pojo.entity.Question;

import java.util.List;

public interface IQuestionService extends IService<Question> {

    public Boolean computeWeighRatio(int id);
    public List<Article> returnQuestionToWebByWeighRatio(String time);
}
