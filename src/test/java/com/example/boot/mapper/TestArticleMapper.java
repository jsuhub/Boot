package com.example.boot.mapper;


import com.example.boot.pojo.entity.Article;
import com.example.boot.service.impl.ArticleServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
