package com.example.boot.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.boot.pojo.entity.Article;
import com.example.boot.pojo.entity.Question;
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
     *
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
     *
     * @param id 文章id
     * @return true/false
     */
    @DeleteMapping("/{id}")
    ResponseVO<Boolean> removeArticleById(@PathVariable int id) {
        boolean remove = articleService.removeById(id);
        return remove
                ? new ResponseVO<Boolean>(Status.SUCCESS, "remove ok", remove)
                : new ResponseVO<Boolean>(Status.ERROR, "remove ok", remove);
    }

    /**
     * 增加文章
     *
     * @param articleRequestVO 请求体参数
     * @return true/false
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
     *
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
     *
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
     *
     * @return 一系列文章
     */
//    @GetMapping("/list/hot")
    ResponseVO<List<Article>> listArticleByHot() {
        List<Article> articleList = articleService.articleListByHot();
        return articleList != null
                ? new ResponseVO<List<Article>>(Status.SUCCESS, "list a user", articleList)
                : new ResponseVO<List<Article>>(Status.ERROR, "list a user", articleList);
    }

    @GetMapping("/list/hot")
    ResponseVO<List<Article>> listArticleByHotPage(@RequestParam("page") int page, @RequestParam("size") int size) {

        IPage iPage = new Page(page, size);
        QueryWrapper<Article> articleQueryWrapper = new QueryWrapper<>();
        articleQueryWrapper.eq("tag", "rear-end");
        IPage page1 = articleService.page(iPage, articleQueryWrapper);
        List records = page1.getRecords();
        return records != null
                ? new ResponseVO<List<Article>>(Status.SUCCESS, "list a user", records)
                : new ResponseVO<List<Article>>(Status.ERROR, "list a user", records);
    }

    /**
     * 添加喜欢
     *
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
     *
     * @param id 文章Id
     * @return 返回参数
     */
    @GetMapping("/cancelLikeAmount/{id}")
    ResponseVO<Integer> cancleLikeAmount(@PathVariable int id) {
        Integer integer = articleService.cancelArticle(id);
        return integer >= 0
                ? new ResponseVO<Integer>(Status.SUCCESS, "cancel successfully", integer)
                : new ResponseVO<Integer>(Status.ERROR, "cancel error", integer);
    }


    /**
     * 根据当天发布的文章的权重返回降序后的所有文章
     *
     * @param time 时间
     * @return ResponseVO<List < Article>> 响应数据实体
     */
    @GetMapping("/hotArticle/{time}")
    ResponseVO<List<Article>> returnArticleToWebByweighRatio(@PathVariable String time) {
        //“2023-6-28-17-51-23”   //查这天的所有文章，根据这天文章的权重返回排序后的文章集合
        List<Article> articles = articleService.returnArticleToWebByweighRatio(articleService.timeFormat(time));
        return articles != null
                ? new ResponseVO<List<Article>>(Status.SUCCESS, "desc successfully", articles)
                : new ResponseVO<List<Article>>(Status.ERROR, "desc error", articles);
    }


    /**
     * 根据文章的id计算该文章的权重(热度)
     *
     * @param id 文章的唯一标识
     * @return ResponseVO<Boolean> 响应数据实体
     */
    @GetMapping("/compute/{id}")
    ResponseVO<Boolean> computeWeighRatio(@PathVariable int id) {
        Boolean aBoolean = articleService.computeWeighRatio(id);
        return aBoolean
                ? new ResponseVO<Boolean>(Status.SUCCESS, "compute successfully", aBoolean)
                : new ResponseVO<Boolean>(Status.ERROR, "compute weigh Ratio fail", aBoolean);
    }


    @GetMapping("/list/{page}/{size}")
    ResponseVO<List> listArticleByPage(@PathVariable int page, @PathVariable int size) {
        IPage iPage = new Page(page, size);
        IPage page1 = articleService.page(iPage, null);
        List records = page1.getRecords();
        return page1 != null
                ? new ResponseVO<>(Status.SUCCESS, "list articles", records)
                : new ResponseVO<>(Status.ERROR, "list articles", records);
    }

    @GetMapping("/list/page")
    ResponseVO<List> listArticleByPageQ(@RequestParam("page") int page, @RequestParam("size") int size) {
        IPage iPage = new Page(page, size);
        IPage page1 = articleService.page(iPage, null);
        List records = page1.getRecords();
        return page1 != null
                ? new ResponseVO<>(Status.SUCCESS, "list articles", records)
                : new ResponseVO<>(Status.ERROR, "list articles", records);
    }

    @GetMapping("/search/{articleTag}")
    ResponseVO<List<Article>> articleTagSelectAll(@PathVariable String articleTag, @RequestParam("page") int page,
                                                  @RequestParam("size") int size) {
        List<Article> articleList = articleService.listByArticleTag(articleTag, page, size);
        return articleList != null
                ? new ResponseVO<>(Status.SUCCESS, "Asc successfully", articleList)
                : new ResponseVO<>(Status.SUCCESS, "Asc successfully", articleList);
    }

    /**
     * 根据时间降序返回当前库中所有的文章
     *
     * @return ResponseVO<List < Article>> 响应数据实体
     */
    @GetMapping("/timeArticle")
    ResponseVO<List<Article>> returnArticleByTimeAsc() {
        List<Article> article = articleService.returnArticleByTimeDesc();
        return article != null
                ? new ResponseVO<>(Status.SUCCESS, "Asc successfully", article)
                : new ResponseVO<>(Status.SUCCESS, "Asc successfully", article);
    }
}
