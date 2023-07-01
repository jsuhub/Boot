package com.example.boot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.boot.pojo.entity.Article;
import com.example.boot.pojo.entity.Question;

import java.util.Date;
import java.util.List;

public interface IArticleService extends IService<Article> {

    public Boolean computeHot(int id);

}
