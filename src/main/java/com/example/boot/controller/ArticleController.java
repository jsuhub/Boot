package com.example.boot.controller;

import com.example.boot.pojo.entity.Article;
import com.example.boot.pojo.entity.Follow;
import com.example.boot.pojo.vo.RequestVO;
import com.example.boot.pojo.vo.ResponseVO;
import com.example.boot.constant.Status;
import com.example.boot.service.impl.ArticleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/articles")
public class ArticleController {

    @Autowired
    private ArticleServiceImpl articleService;

    @PostMapping
    ResponseVO<Boolean> saveArticle(@RequestBody RequestVO<Article> articleRequestVO) {
        System.out.println(articleRequestVO.getData());
        boolean save = articleService.save(articleRequestVO.getData());
        return save
                ? new ResponseVO<Boolean>(Status.SUCCESS, "save ok", save)
                : new ResponseVO<Boolean>(Status.ERROR, "save error", save);
    }

    @DeleteMapping("/{id}")
    ResponseVO<Boolean> removeArticleById(@PathVariable int id) {
        boolean remove = articleService.removeById(id);
        return remove
                ? new ResponseVO<Boolean>(Status.SUCCESS, "remove ok", remove)
                : new ResponseVO<Boolean>(Status.ERROR, "remove ok", remove);
    }

    @PutMapping
    ResponseVO<Boolean> updateArticle(@RequestBody RequestVO<Article> articleRequestVO) {
        boolean update = articleService.updateById(articleRequestVO.getData());
        return update
                ? new ResponseVO<Boolean>(Status.SUCCESS, "update ok", update)
                : new ResponseVO<Boolean>(Status.ERROR, "update error", update);
    }

    @GetMapping("/{id}")
    ResponseVO<Article> getArticleById(@PathVariable int id) {
        Article article = articleService.getById(id);
        return article != null
                ? new ResponseVO<Article>(Status.SUCCESS, "get a user", article)
                : new ResponseVO<Article>(Status.ERROR, "get a user", article);
    }

    @GetMapping("/list")
    ResponseVO<List<Article>> listArticle() {
        List<Article> articleList = articleService.list();
        return articleList != null
                ? new ResponseVO<List<Article>>(Status.SUCCESS, "list a user", articleList)
                : new ResponseVO<List<Article>>(Status.ERROR, "list a user", articleList);
    }

    @GetMapping("/listbyhot")
    ResponseVO<List<Article>> listArticleByHot() {
        List<Article> articleList = articleService.articleListByHot();
        return articleList != null
                ? new ResponseVO<List<Article>>(Status.SUCCESS, "list a user", articleList)
                : new ResponseVO<List<Article>>(Status.ERROR, "list a user", articleList);
    }

    @GetMapping("/addLikeAmount/{id}")
    ResponseVO<Integer> addLikeAmount(@PathVariable int id) {
        Integer integer = articleService.articleLike(id);
        return integer >= 0
                ? new ResponseVO<Integer>(Status.SUCCESS, "like successfully", integer)
                : new ResponseVO<Integer>(Status.ERROR, "like error", integer);
    }

    @GetMapping("/cancelLikeAmount/{id}")
    ResponseVO<Integer> cancleLikeAmount(@PathVariable int id) {
        Integer integer = articleService.cancelArticle(id);
        return integer >= 0
                ? new ResponseVO<Integer>(Status.SUCCESS, "cancel successfully", integer)
                : new ResponseVO<Integer>(Status.ERROR, "cancel error", integer);
    }


}