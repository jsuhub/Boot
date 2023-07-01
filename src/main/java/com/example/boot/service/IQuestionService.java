package com.example.boot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.boot.pojo.entity.Article;
import com.example.boot.pojo.entity.Question;
import com.example.boot.pojo.vo.ResponseVO;

import java.util.List;

import java.util.List;

public interface IQuestionService extends IService<Question> {


    public Boolean computeHot(int id) ;

}
