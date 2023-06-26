package com.example.boot.controller;

import com.example.boot.pojo.entity.Article;
import com.example.boot.pojo.vo.Code;
import com.example.boot.pojo.vo.Message;
import com.example.boot.pojo.vo.ResponseVO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/articles")
public class ArticleController {

    @PostMapping
    ResponseVO<Boolean> saveArticle () {
        ResponseVO<Boolean> notFound = new ResponseVO<>(404, "NOT Found", false);
        System.out.println(notFound);
        return null;
    }

    @DeleteMapping
    ResponseVO<Boolean> removeArticleById () {

        Boolean b = true;

        return b
                ? new ResponseVO<>(Code.SUCCESS, Message.SUCCESS, true)
                : new ResponseVO<>(Code.SUCCESS,
                Message.SUCCESS, false);
    }

    @PutMapping
    Boolean updateArticle () {
        return null;
    }

    @GetMapping("/{id}")
    ResponseVO<Article> getArticleById (@PathVariable int id) {

        Article article = new Article();
        article.setName("小王子");

        ResponseVO<Article> articleResponseVO = new ResponseVO<>(404, "NOT Found", article);
        System.out.println(articleResponseVO);

        return articleResponseVO;
    }

    @GetMapping("/list")
    List<Article> listArticle () {
        return null;
    }

    @GetMapping("/test")
    ResponseVO<String> test() {
        ResponseVO<String> stringResponseVO = new ResponseVO<>();
        return stringResponseVO;
    }

}
