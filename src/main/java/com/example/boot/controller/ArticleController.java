package com.example.boot.controller;

import com.example.boot.pojo.entity.Article;
import com.example.boot.pojo.vo.Status;
import com.example.boot.pojo.vo.StatusInfo;
import com.example.boot.pojo.vo.ResponseVO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/articles")
public class ArticleController {

    @PostMapping
    ResponseVO<Boolean> saveArticle () {
        return null;
    }

    @DeleteMapping
    ResponseVO<Boolean> removeArticleById () {
        return null;
    }

    @PutMapping
    Boolean updateArticle () {
        return null;
    }

    @GetMapping("/{id}")
    ResponseVO<Article> getArticleById (@PathVariable int id) {
        return null;
    }

    @GetMapping("/list")
    List<Article> listArticle () {
        return null;
    }

    @GetMapping("/test")
    ResponseVO<String> test() {
        return null;
    }

}
