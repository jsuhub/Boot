package com.example.boot.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.boot.mapper.ArticleMapper;
import com.example.boot.pojo.entity.Article;
import com.example.boot.service.IArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements IArticleService{
    @Autowired
    ArticleMapper articleMapper;
    @Override
    public Boolean computeWeighRatio(int id) {   //前端传过来一个文章id，计算这篇文章的权重。并更新这篇文章的数据(加入权重字段)
        int weighRatio;
        Article article = articleMapper.selectById(id);
        Integer likeAmount = article.getLikeAmount();
        Integer starAmount = article.getStarAmount();
        Integer browserAmount = article.getBrowserAmount();
        weighRatio=likeAmount*3+starAmount*2+browserAmount;
        return articleMapper.updateWeighRatio(id,weighRatio) == 1
                ? true
                : false;
    }


    public List<Article> returnArticleToWebByweighRatio(String time){
        return articleMapper.getArticleByTimeAndHot(time);
    }


    public String timeFormat(String data){
        String time;
        String[] parts = data.split("-");//data="2023-6-29-1-29-45"
        int year = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int day = Integer.parseInt(parts[2]);
        time=year+"-"+month+"-"+day;
        return time+'%';
    }



}
