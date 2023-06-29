package com.example.boot.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
@CrossOrigin
@RequestMapping("/articles")
public class ArticleController {

    @Autowired
    private ArticleServiceImpl articleService;

    /**
     * 插入文章
     * @param articleRequestVO 文章的请求参数
     * @return true/false
     */
    @PostMapping
    ResponseVO<Boolean> saveArticle(@RequestBody RequestVO<Article> articleRequestVO) {
        System.out.println(articleRequestVO.getData());
        boolean save = articleService.save(articleRequestVO.getData());
        return save
                ? new ResponseVO<Boolean>(Status.SUCCESS, "save ok", save)
                : new ResponseVO<Boolean>(Status.ERROR, "save error", save);
    }

    /**
     * 删除文章
     * @param id 文章id
     * @return  true/false
     */
    @DeleteMapping("/{id}")
    ResponseVO<Boolean> removeArticleById(@PathVariable int id ) {
        boolean remove = articleService.removeById(id);
        return remove
                ? new ResponseVO<Boolean>(Status.SUCCESS, "remove ok", remove)
                : new ResponseVO<Boolean>(Status.ERROR, "remove ok", remove);
    }

    /**
     * 增加文章
     * @param articleRequestVO  请求体参数
     * @return  true/false
     */
    @PutMapping
    ResponseVO<Boolean> updateArticle(@RequestBody RequestVO<Article> articleRequestVO) {
        boolean update = articleService.updateById(articleRequestVO.getData());
        return update
                ? new ResponseVO<Boolean>(Status.SUCCESS, "update ok", update)
                : new ResponseVO<Boolean>(Status.ERROR, "update error", update);
    }

    /**
     * 得到文章内容
     * @param id
     * @return 文章实体
     */
    @GetMapping("/{id}")
    ResponseVO<Article> getArticleById(@PathVariable int id) {
        Article article = articleService.getById(id);
        return article != null
                ? new ResponseVO<Article>(Status.SUCCESS, "get a user", article)
                : new ResponseVO<Article>(Status.ERROR, "get a user", article);
    }

    /**
     * 展示一系列文章实体
     * @return 一系列文章
     */
    @GetMapping("/list")
    ResponseVO<List<Article>> listArticle() {
        List<Article> articleList = articleService.list();
        return articleList != null
                ? new ResponseVO<List<Article>>(Status.SUCCESS, "list a user", articleList)
                : new ResponseVO<List<Article>>(Status.ERROR, "list a user", articleList);
    }

    /**
     * 按照文章热度排名
     * @return  一系列文章
     */
    @GetMapping("/listbyhot")
    ResponseVO<List<Article>> listArticleByHot() {
        List<Article> articleList = articleService.articleListByHot();
        return articleList != null
                ? new ResponseVO<List<Article>>(Status.SUCCESS, "list a user", articleList)
                : new ResponseVO<List<Article>>(Status.ERROR, "list a user", articleList);
    }

    /**
     * 添加喜欢
     * @param id 文章Id
     * @return 返回参数
     */
    @GetMapping("/addLikeAmount/{id}")
    ResponseVO<Integer> addLikeAmount(@PathVariable int id) {
        Integer integer = articleService.articleLike(id);
        return integer >= 0
                ? new ResponseVO<Integer>(Status.SUCCESS, "like successfully", integer)
                : new ResponseVO<Integer>(Status.ERROR, "like error", integer);
    }

    /**
     * 取消点赞量
     * @param id 文章Id
     * @return  返回参数
     */
    @GetMapping("/cancelLikeAmount/{id}")
    ResponseVO<Integer> cancleLikeAmount(@PathVariable int id) {
        Integer integer = articleService.cancelArticle(id);
        return integer >= 0
                ? new ResponseVO<Integer>(Status.SUCCESS, "cancel successfully", integer)
                : new ResponseVO<Integer>(Status.ERROR, "cancel error", integer);
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


    @GetMapping("/list/{page}")
    ResponseVO<List> listArticleByPage(@PathVariable int page) {
        IPage iPage = new Page(page, 5);
        IPage page1 = articleService.page(iPage, null);
        List records = page1.getRecords();
        return page1 != null
                ? new ResponseVO<>(Status.SUCCESS, "list articles", records)
                : new ResponseVO<>(Status.ERROR, "list articles", records);
    }

    @GetMapping("/search/{articleTag}")
    ResponseVO<List<Article>> articleTagSelectAll(@PathVariable String articleTag){
        List<Article> articleList = articleService.listByArticleTag(articleTag);
        System.out.println(articleList);
        ResponseVO<List<Article>>  responseVO=null;
        if(articleList.size()==0){
        responseVO  = new ResponseVO<>(Status.ERROR, "Tage is null", articleList);
             }else {
            responseVO = new ResponseVO<>(Status.SUCCESS, " successfully", articleList);
             }
              return responseVO;
}
}
