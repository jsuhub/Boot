package com.example.boot.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
        List<Article> articles = articleService.returnArticleToWebByweighRatio("2023-06-26",0,5);
        System.out.println(articles);
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
    void testSelectPage() {
        IPage iPage = new Page(0, 5);
        IPage page = articleService.page(iPage, null);
        System.out.println(page);
    }

}
