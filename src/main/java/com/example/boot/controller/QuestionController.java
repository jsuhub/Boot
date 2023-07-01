package com.example.boot.controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.boot.constant.Status;
import com.example.boot.constant.StatusInfo;
import com.example.boot.pojo.entity.Article;
import com.example.boot.pojo.entity.Question;
import com.example.boot.pojo.vo.RequestVO;
import com.example.boot.pojo.vo.ResponseVO;
import com.example.boot.service.impl.ArticleServiceImpl;
import com.example.boot.service.impl.QuestionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/questions")
public class QuestionController {


    @Autowired
    private QuestionServiceImpl questionService;

    /**
     * 插入问题
     *
     * @param articleRequestVO 问题的请求参数
     * @return Boolean 布尔值 是否插入成功
     */
    @PostMapping
    ResponseVO<Boolean> saveQuestion(@RequestBody RequestVO<Question> articleRequestVO) {
        boolean saveArticle = questionService.save(articleRequestVO.getData());
        return saveArticle
                ? new ResponseVO<>(Status.SUCCESS, StatusInfo.SAVE_INFO_SUCCESS, saveArticle)
                : new ResponseVO<>(Status.ERROR, StatusInfo.SAVE_INFO_FAILED, saveArticle);
    }

    /**
     * 通过id删除问题
     *
     * @param id 问题id
     * @return Boolean 布尔值 是否删除成功
     */
    @DeleteMapping("/{id}")
    ResponseVO<Boolean> removeQuestionById(@PathVariable int id) {
        boolean removeQuestion = questionService.removeById(id);
        return removeQuestion
                ? new ResponseVO<>(Status.SUCCESS, StatusInfo.REMOVE_INFO_SUCCESS, removeQuestion)
                : new ResponseVO<>(Status.ERROR, StatusInfo.REMOVE_INFO_FAILED, removeQuestion);
    }

    /**
     * 修改问题
     *
     * @param questionRequestVO 请求体参数
     * @return Boolean 布尔值 是否修改成功
     */
    @PutMapping
    ResponseVO<Boolean> updateArticle(@RequestBody RequestVO<Question> questionRequestVO) {
        boolean updateArticle = questionService.updateById(questionRequestVO.getData());
        Boolean setHotBoolean = questionService.computeHot(questionRequestVO.getData().getId());
        if (!setHotBoolean) {
            return new ResponseVO<>(Status.ERROR, "update error", setHotBoolean);
        }
        return updateArticle
                ? new ResponseVO<>(Status.SUCCESS, "update ok", updateArticle)
                : new ResponseVO<>(Status.ERROR, "update error", updateArticle);
    }

    /**
     * 通过id查询一则问题
     *
     * @param id 问题id
     * @return Question 单篇问题
     */
    @GetMapping("/{id}")
    ResponseVO<Question> getQuestionById(@PathVariable int id) {
        Question question = questionService.getById(id);
        return  question != null
                ? new ResponseVO<>(Status.SUCCESS, "get a user", question)
                : new ResponseVO<>(Status.ERROR, "get a user", question);
    }

    /**
     * 获取所有问题
     *
     * @return List<Question> 问题数组
     */
    @GetMapping("/list")
    ResponseVO<List<Question>> listQuestion() {
        List<Question> list = questionService.list();
        return list != null
                ? new ResponseVO<>(Status.SUCCESS, "list a user", list)
                : new ResponseVO<>(Status.ERROR, "list a user", list);
    }

    /**
     * 获取所有问题+分页
     *
     * @param page 页数
     * @param size 数据条数
     * @return List
     */
    @GetMapping("/list/page")
    ResponseVO<List<Question>> listQuestionByPage(@RequestParam("page") int page, @RequestParam("size") int size) {
        IPage iPage = new Page(page, size);
        QueryWrapper<Question> questionQueryWrapper = new QueryWrapper<>();
        IPage listPage = questionService.page(iPage, questionQueryWrapper);
        List records = listPage.getRecords();
        return listPage != null
                ? new ResponseVO<List<Question>>(Status.SUCCESS, "list articles", records)
                : new ResponseVO<List<Question>>(Status.ERROR, "list articles", records);
    }

    /**
     * 获取精选问题+分页
     * 通过hot排序所有文章
     *
     * @param page 页数
     * @param size 数据条数
     * @return List<Article> 文章数组
     */
    @GetMapping("/list/feature")
    ResponseVO<List<Question>> listFeatureQuestionByPage(@RequestParam("page") int page, @RequestParam("size") int size) {
        IPage iPage = new Page(page, size);
        QueryWrapper<Question> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("hot");
        IPage featurePage = questionService.page(iPage, queryWrapper);
        List records = featurePage.getRecords();
        return records != null
                ? new ResponseVO<List<Question>>(Status.SUCCESS, "list a user", records)
                : new ResponseVO<List<Question>>(Status.ERROR, "list a user", records);
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
    ResponseVO<List<Question>> listLatestQuestionByPage(@RequestParam("page") int page, @RequestParam("size") int size) {
        IPage iPage = new Page(page, size);
        QueryWrapper<Question> queryQueryWrapper = new QueryWrapper<>();
        queryQueryWrapper.orderByDesc("publish_date");
        IPage latestPage = questionService.page(iPage, queryQueryWrapper);
        List records = latestPage.getRecords();
        return records != null
                ? new ResponseVO<List<Question>>(Status.SUCCESS, "list a user", records)
                : new ResponseVO<List<Question>>(Status.ERROR, "list a user", records);
    }

    /**
     * 获取当天根据问题排序后的所有文章+分页
     *
     * @param date 日期
     * @param page 页数
     * @param size 数据条数
     * @return List<Question> 文章数组
     */
    @GetMapping("/list/hot/{date}")
    ResponseVO<List<Question>> listHotQuestionByPage(@PathVariable String date, @RequestParam("page") int page,
                                                   @RequestParam("size") int size) {
        IPage iPage = new Page(page, size);
        QueryWrapper<Question> questionQueryWrapper = new QueryWrapper<>();
        questionQueryWrapper.orderByDesc("hot");
        questionQueryWrapper.like("publish_date", date + "%");
        IPage hotPage = questionService.page(iPage, questionQueryWrapper);
        List records = hotPage.getRecords();
        return records != null
                ? new ResponseVO<List<Question>>(Status.SUCCESS, "list a user", records)
                : new ResponseVO<List<Question>>(Status.ERROR, "list a user", records);
    }

}
