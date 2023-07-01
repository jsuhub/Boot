package com.example.boot.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.boot.constant.StatusInfo;
import com.example.boot.pojo.entity.Article;
import com.example.boot.pojo.vo.RequestVO;
import com.example.boot.pojo.vo.ResponseVO;
import com.example.boot.constant.Status;
import com.example.boot.service.impl.ArticleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
     * @return ResponseVO<Boolean> 是否插入成功
     */
    @PostMapping
    ResponseVO<Boolean> saveArticle(@RequestBody RequestVO<Article> articleRequestVO) {
        boolean saveArticle = articleService.save(articleRequestVO.getData());
        return saveArticle
                ? new ResponseVO<>(Status.SUCCESS, StatusInfo.SAVE_INFO_SUCCESS, saveArticle)
                : new ResponseVO<>(Status.ERROR, StatusInfo.SAVE_INFO_FAILED, saveArticle);
    }

    /**
     * 通过id删除文章
     *
     * @param id 文章id
     * @return ResponseVO<Boolean> 布尔值 是否删除成功
     */
    @DeleteMapping("/{id}")
    ResponseVO<Boolean> removeArticleById(@PathVariable int id) {
        boolean removeArticle = articleService.removeById(id);
        return removeArticle
                ? new ResponseVO<>(Status.SUCCESS, StatusInfo.REMOVE_INFO_SUCCESS, removeArticle)
                : new ResponseVO<>(Status.ERROR, StatusInfo.REMOVE_INFO_FAILED, removeArticle);
    }

    /**
     * 修改文章信息
     *
     * @param articleRequestVO 请求体参数
     * @return ResponseVO<Boolean> 布尔值 是否修改成功
     */
    @PutMapping
    ResponseVO<Boolean> updateArticle(@RequestBody RequestVO<Article> articleRequestVO) {
        boolean updateArticle = articleService.updateById(articleRequestVO.getData());
        return updateArticle
                ? new ResponseVO<>(Status.SUCCESS, "update ok", updateArticle)
                : new ResponseVO<>(Status.ERROR, "update error", updateArticle);
    }


    /**
     * 通过id查询一篇文章
     *
     * @param id 文章id
     * @return ResponseVO<Article> 文章实体
     */
    @GetMapping("/{id}")
    ResponseVO<Article> getArticleById(@PathVariable int id) {
        Article article = articleService.getById(id);
        return article != null
                ? new ResponseVO<>(Status.SUCCESS, "get a user", article)
                : new ResponseVO<>(Status.ERROR, "get a user", article);
    }

    /**
     * 获取所有文章
     *
     * @return ResponseVO<List<Article>> 文章数组
     */
    @GetMapping("/list")
    ResponseVO<List<Article>> listArticle() {
        List<Article> articleList = articleService.list();
        return articleList != null
                ? new ResponseVO<>(Status.SUCCESS, "list a user", articleList)
                : new ResponseVO<>(Status.ERROR, "list a user", articleList);
    }

    /**
     * 获取按照发布时间降序排序后的所有文章+分页
     *
     * @param page 页数
     * @param size 数据条数
     * @return ResponseVO<List<Article>> 文章数组
     */
    @GetMapping("/list/latest")
    ResponseVO<List<Article>> listArticleForLatest(@RequestParam("page") int page, @RequestParam("size") int size) {
        IPage iPage = new Page(page, size);
        QueryWrapper<Article> articleQueryWrapper = new QueryWrapper<>();
        articleQueryWrapper.orderByDesc("publish_date");
        IPage latestPage = articleService.page(iPage, articleQueryWrapper);
        List records = latestPage.getRecords();
        return records != null
                ? new ResponseVO<List<Article>>(Status.SUCCESS, "list a user", records)
                : new ResponseVO<List<Article>>(Status.ERROR, "list a user", records);
    }

    /**
     * 获取根据热度排序后的所有文章+分页
     *
     * @param page 页数
     * @param size 数据条数
     * @return ResponseVO<List<Article>> 文章数组
     */
    @GetMapping("/list/feature")
    ResponseVO<List<Article>> listArticleByHot(@RequestParam("page") int page, @RequestParam("size") int size) {
        IPage iPage = new Page(page, size);
        QueryWrapper<Article> articleQueryWrapper = new QueryWrapper<>();
        articleQueryWrapper.orderByDesc("hot");
        IPage page1 = articleService.page(iPage, articleQueryWrapper);
        List records = page1.getRecords();
        return records != null
                ? new ResponseVO<>(Status.SUCCESS, "list a user", records)
                : new ResponseVO<>(Status.ERROR, "list a user", records);
    }

    /**
     * 获取根据热度排序后的当天所有文章+分页
     * @param time 日期
     * @param page  已经展示文章的数量
     * @param size  返回文章的大小
     * @return 一系列文章
     */
    @GetMapping("/list/hot/{time}")
    ResponseVO<List<Article>> returnArticleToWebByweighRatio(@PathVariable String time,@RequestParam("page") int page,
                                                             @RequestParam("size") int size){
        //“2023-6-28-17-51-23”   //查这天的所有文章，根据这天文章的权重返回排序后的文章集合
        List<Article> articles = articleService.returnArticleToWebByweighRatio(articleService.timeFormat(time),page,size);
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

    /**
     * 根据文章的标签返回一系列文章
     * @param articleTag 文章标签
     * @param page 返回的跳转页数
     * @param size 页数大小
     * @return
     */
    @GetMapping("/search/{articleTag}")
    ResponseVO<List<Article>> articleTagSelectAll(@PathVariable String articleTag, @RequestParam("page") int page,
                                                  @RequestParam("size") int size) {
//        List<Article> articleList = articleService.listByArticleTag(articleTag, page, size);
        return null != null
                ? new ResponseVO<>(Status.SUCCESS, "Asc successfully", null)
                : new ResponseVO<>(Status.SUCCESS, "Asc successfully", null);
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

    /**
     * 按照页表展示关注人推出的问题
     * @param userId 用户Id
     * @param page  页数
     * @param size  大小
     * @return
     */
    @GetMapping("/follow/{userId}")
    ResponseVO<List<Article>> articleFollower(@PathVariable int userId, @RequestParam("page") int page,
                                                  @RequestParam("size") int size){
        return null == null
                ? new ResponseVO<>(Status.SUCCESS,"Asc successfully", null)
                : new ResponseVO<>(Status.SUCCESS,"Asc successfully", null);
    }

}
