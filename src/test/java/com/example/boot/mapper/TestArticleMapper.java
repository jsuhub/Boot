package com.example.boot.mapper;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.boot.pojo.entity.Article;
import com.example.boot.service.impl.ArticleServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class TestArticleMapper {

    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    ArticleServiceImpl articleService;
    @Test
    void test() {
        Article article = new Article();
        article.setTitle("yuliang");
        articleMapper.insert(article);

    }
    @Test
    void testSelectPage() {
        IPage iPage = new Page(0 ,5);
        IPage iPage1 = articleMapper.selectPage(iPage, null);
        System.out.println(iPage1);
//        articleMapper.selectPage(1, 2)
    }

    @Test
    void testSelectByMyPage() {
        List<Article> articleList = articleMapper.selectByMyPage(0, 10);
        System.out.println(articleList);

    }

    @Test
    void insertWeightRatio(){
        Integer integer = articleMapper.updateWeighRatio(-100622334,3);
        System.out.println(integer);
    }


    @Test
    void testTimeFormat(){
        articleService.timeFormat("2021-2-23-5-6-17");
    }

    @Test
    void testTimeFormat2(){
        articleMapper.getArticleByTimeAndHot("2023-6-29%");
    }
}
