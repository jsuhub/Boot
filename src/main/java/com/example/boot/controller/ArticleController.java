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
     * @return Boolean 布尔值 是否插入成功
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
     * @return Boolean 布尔值 是否删除成功
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
     * @return Boolean 布尔值 是否修改成功
     */
    @PutMapping
    ResponseVO<Boolean> updateArticle(@RequestBody RequestVO<Article> articleRequestVO) {
        boolean updateArticle = articleService.updateById(articleRequestVO.getData());

        Boolean setHotBoolean = articleService.computeHot(articleRequestVO.getData().getId());

        if (!setHotBoolean) {
            return new ResponseVO<>(Status.ERROR, "update error", setHotBoolean);
        }

        return updateArticle
                ? new ResponseVO<>(Status.SUCCESS, "update ok", updateArticle)
                : new ResponseVO<>(Status.ERROR, "update error", updateArticle);
    }

    /**
     * 通过id查询一篇文章
     *
     * @param id 文章id
     * @return Article 单篇文章
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
     * @return List<Article> 文章数组
     */
    @GetMapping("/list")
    ResponseVO<List<Article>> listArticle() {
        List<Article> articleList = articleService.list();
        return articleList != null
                ? new ResponseVO<>(Status.SUCCESS, "list a user", articleList)
                : new ResponseVO<>(Status.ERROR, "list a user", articleList);
    }

    /**
     * 获取所有文章+分页
     *
     * @param page 页数
     * @param size 数据条数
     * @return List
     */
    @GetMapping("/list/page")
    ResponseVO<List<Article>> listArticleByPage(@RequestParam("page") int page, @RequestParam("size") int size) {
        IPage iPage = new Page(page, size);
        QueryWrapper<Article> articleQueryWrapper = new QueryWrapper<>();
        IPage listPage = articleService.page(iPage, articleQueryWrapper);
        List records = listPage.getRecords();
        return listPage != null
                ? new ResponseVO<List<Article>>(Status.SUCCESS, "list articles", records)
                : new ResponseVO<List<Article>>(Status.ERROR, "list articles", records);
    }

    /**
     * 获取精选文章+分页
     * 通过hot排序所有文章
     *
     * @param page 页数
     * @param size 数据条数
     * @return List<Article> 文章数组
     */
    @GetMapping("/list/feature")
    ResponseVO<List<Article>> listFeatureArticleByPage(@RequestParam("page") int page, @RequestParam("size") int size) {
        IPage iPage = new Page(page, size);
        QueryWrapper<Article> articleQueryWrapper = new QueryWrapper<>();
        articleQueryWrapper.orderByDesc("hot");
        IPage featurePage = articleService.page(iPage, articleQueryWrapper);
        List records = featurePage.getRecords();
        return records != null
                ? new ResponseVO<List<Article>>(Status.SUCCESS, "list a user", records)
                : new ResponseVO<List<Article>>(Status.ERROR, "list a user", records);
    }

    /**
     * 获取最新文章+分页
     * 通过hot排序所有文章
     *
     * @param page 页数
     * @param size 数据条数
     * @return List<Article> 文章数组
     */
    @GetMapping("/list/latest")
    ResponseVO<List<Article>> listLatestArticleByPage(@RequestParam("page") int page, @RequestParam("size") int size) {
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
     * 获取当天根据热度排序后的所有文章+分页
     *
     * @param date 日期
     * @param page 页数
     * @param size 数据条数
     * @return List<Article> 文章数组
     */
    @GetMapping("/list/hot/{date}")
    ResponseVO<List<Article>> listHotArticleByPage(@PathVariable String date, @RequestParam("page") int page,
                                                    @RequestParam("size") int size) {
        IPage iPage = new Page(page, size);
        QueryWrapper<Article> articleQueryWrapper = new QueryWrapper<>();
        articleQueryWrapper.orderByDesc("hot");
        articleQueryWrapper.like("publish_date", date + "%");
        IPage hotPage = articleService.page(iPage, articleQueryWrapper);
        List records = hotPage.getRecords();
        return records != null
                ? new ResponseVO<List<Article>>(Status.SUCCESS, "list a user", records)
                : new ResponseVO<List<Article>>(Status.ERROR, "list a user", records);
    }

    /**
     * 根据标签获取所有文章+分页
     *
     * @param page 页数
     * @param size 数据条数
     * @return
     */
    @GetMapping("/list/tag/{tag}")
    ResponseVO<List<Article>> listArticleByTag(@PathVariable String tag, @RequestParam("page") int page,
                                                  @RequestParam("size") int size) {
        IPage iPage = new Page(page, size);
        QueryWrapper<Article> articleQueryWrapper = new QueryWrapper<>();
        articleQueryWrapper.eq("tag", tag);
        IPage<Article> tagPage = articleService.page(iPage, articleQueryWrapper);
        List<Article> records = tagPage.getRecords();
        return records != null
                ? new ResponseVO<>(Status.SUCCESS, "Asc successfully", records)
                : new ResponseVO<>(Status.SUCCESS, "Asc successfully", records);
    }

    /**
     * 根据标签获取精选文章+分页
     *
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/list/{tag}/feature")
    ResponseVO<List<Article>> listFeatureArticleByTagAndPage(@PathVariable String tag,@RequestParam("page") int page,
                                                     @RequestParam("size") int size) {
        IPage iPage = new Page(page, size);
        QueryWrapper<Article> articleQueryWrapper = new QueryWrapper<>();
        articleQueryWrapper.eq("tag", tag);
        articleQueryWrapper.orderByDesc("hot");
        IPage<Article> tagPage = articleService.page(iPage, articleQueryWrapper);
        List<Article> records = tagPage.getRecords();
        return records != null
                ? new ResponseVO<>(Status.SUCCESS, "Asc successfully", records)
                : new ResponseVO<>(Status.SUCCESS, "Asc successfully", records);
    }

    /**
     * 根据标签获取最新文章+分页
     *
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/list/{tag}/latest")
    ResponseVO<List<Article>> listLatestArticleByTagAndPage(@PathVariable String tag, @RequestParam("page") int page,
                                                  @RequestParam("size") int size) {
        IPage iPage = new Page(page, size);
        QueryWrapper<Article> articleQueryWrapper = new QueryWrapper<>();
        articleQueryWrapper.eq("tag", tag);
        articleQueryWrapper.orderByAsc("publish_date");
        IPage<Article> tagPage = articleService.page(iPage, articleQueryWrapper);
        List<Article> records = tagPage.getRecords();
        return records != null
                ? new ResponseVO<>(Status.SUCCESS, "Asc successfully", records)
                : new ResponseVO<>(Status.SUCCESS, "Asc successfully", records);
    }

    /**
     * 根据标签获取热门文章+分页
     *
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/list/{tag}/hot/{date}")
    ResponseVO<List<Article>> listHotArticleByTagAndPage(@PathVariable String tag, @PathVariable String date,
                                                     @RequestParam("page") int page,
                                                        @RequestParam("size") int size) {
        IPage iPage = new Page(page, size);
        QueryWrapper<Article> articleQueryWrapper = new QueryWrapper<>();
        articleQueryWrapper.eq("tag", tag);
        articleQueryWrapper.like("publish_date", date+"%");
        articleQueryWrapper.orderByDesc("hot");
        IPage<Article> tagPage = articleService.page(iPage, articleQueryWrapper);
        List<Article> records = tagPage.getRecords();
        return records != null
                ? new ResponseVO<>(Status.SUCCESS, "Asc successfully", records)
                : new ResponseVO<>(Status.SUCCESS, "Asc successfully", records);
    }

}
