package com.example.boot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.boot.pojo.entity.Article;

import java.util.Date;
import java.util.List;

public interface IArticleService extends IService<Article> {

    public Boolean computeWeighRatio(int id);

    public List<Article> returnArticleToWebByweighRatio(String time);
}
