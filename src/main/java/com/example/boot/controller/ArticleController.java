package com.example.boot.controller;

import com.example.boot.pojo.entity.Article;
import com.example.boot.pojo.vo.RequestVO;
import com.example.boot.pojo.vo.ResponseVO;
import com.example.boot.constant.Status;
import com.example.boot.service.impl.ArticleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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

    @GetMapping("/hotArticle/{time}")   //根据权重返回降序后的文章集合
    ResponseVO<List<Article>> returnArticleToWebByweighRatio(@PathVariable String time){
        //“2023-6-28-17-51-23”   //查这天的所有文章，根据这天文章的权重返回排序后的文章集合
        List<Article> articles = articleService.returnArticleToWebByweighRatio(articleService.timeFormat(time));
        return articles != null
                ? new ResponseVO<List<Article>>(Status.SUCCESS, "desc successfully", articles)
                : new ResponseVO<List<Article>>(Status.ERROR, "desc error", articles);
    }



    @GetMapping("/compute/{id}")    //根据文章id计算该文章的权重---热度
    ResponseVO<Boolean> computeWeighRatio(@PathVariable int id){
        Boolean aBoolean = articleService.computeWeighRatio(id);
        return aBoolean
                ?new ResponseVO<Boolean>(Status.SUCCESS,"compute successfully",aBoolean)
                :new ResponseVO<Boolean>(Status.ERROR,"compute weigh Ratio fail",aBoolean);
    }


}
