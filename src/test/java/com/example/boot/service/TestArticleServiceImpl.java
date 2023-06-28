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
        article.setTitle("sdsadadsad");
        articleService.save(article);
    }

    @Test
    void testhot(){

        System.out.println(articleService.articleListByHot());
    }

    @Test
    void testlike1(){
        System.out.println(articleService.articleLike(1));
    }

    @Test
    void testlcancel(){
        System.out.println(articleService.cancelArticle(1));
    }
    @Test
    void testsatrt2(){
        System.out.println(articleService.addStarAmount(1));
    }
    @Test
    void testcanl2(){
        System.out.println(articleService.cancelStarAmount(1));
    }
    @Test
    void testbrowser(){
        System.out.println(articleService.addBrowserAmount(1));
    }
    @Test
    void testbroweslike(){
        System.out.println(
                articleService.cancelBrowserAmount(1));
    }
}
