package com.example.boot.service.impl;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.boot.constant.Weight;
import com.example.boot.mapper.ArticleMapper;
import com.example.boot.mapper.FollowMapper;
import com.example.boot.mapper.QuestionMapper;
import com.example.boot.pojo.entity.Article;
import com.example.boot.pojo.entity.Follow;
import com.example.boot.pojo.entity.Question;
import com.example.boot.service.IArticleService;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements IArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    /**
     * 根据文章id计算该文章热度
     *
     * @param id
     * @return
     */
    @Override
    public Boolean computeHot(int id) {
        Article article = articleMapper.selectById(id);
        Integer likeAmount = article.getLikeAmount();
        Integer starAmount = article.getStarAmount();
        Integer browserAmount = article.getBrowserAmount();
        Integer hot = likeAmount * Weight.LIKE + starAmount * Weight.STAR + browserAmount * Weight.BROWSER;
        article.setHot(hot);
        int i = articleMapper.updateById(article);
        return i > 0
                ? true
                : false;
    }

}