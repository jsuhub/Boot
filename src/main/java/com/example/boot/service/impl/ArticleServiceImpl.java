package com.example.boot.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.boot.mapper.ArticleMapper;
import com.example.boot.pojo.entity.Article;
import com.example.boot.service.IArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements IArticleService{
    @Autowired
    private ArticleMapper articleMapper;


}
