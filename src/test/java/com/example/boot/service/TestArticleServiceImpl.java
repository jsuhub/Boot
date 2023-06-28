package com.example.boot.service;

import com.example.boot.pojo.entity.Article;
import com.example.boot.service.impl.ArticleServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class TestArticleServiceImpl {

    @Autowired
    private ArticleServiceImpl articleService;

    @Test
    void test() {
        Article article = new Article();
        article.setTitle("xjf");
        articleService.save(article);
    }

    @Test
    void test2() {
        List<Article> articles = articleService.returnArticleToWebByweighRatio("2023-6-29%");
        System.out.println(articles);
    }

}
