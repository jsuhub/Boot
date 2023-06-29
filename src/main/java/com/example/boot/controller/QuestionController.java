package com.example.boot.controller;

import com.example.boot.constant.Status;
import com.example.boot.pojo.entity.Article;
import com.example.boot.pojo.entity.Question;
import com.example.boot.pojo.vo.ResponseVO;
import com.example.boot.service.impl.QuestionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    @Autowired
    private QuestionServiceImpl questionService;

    @PostMapping    //增一个问题
    ResponseVO<Boolean> saveQuestion(@RequestBody ResponseVO<Question> questionResponseVO){
        boolean save=questionService.save(questionResponseVO.getData());
        return save
                ? new ResponseVO<Boolean>(Status.SUCCESS,"save ok",save)
                : new ResponseVO<Boolean>(Status.ERROR,"save error",save);
    }


    @DeleteMapping("/{id}")   //删一个问题
    ResponseVO<Boolean>removeQuestion(@PathVariable int id){
        boolean remove=questionService.removeById(id);
        return remove
                ?new ResponseVO<Boolean>(Status.SUCCESS,"remove ok",remove)
                :new ResponseVO<>(Status.ERROR,"remove error",remove);
    }


    @PutMapping  //改一个问题
    ResponseVO<Boolean> updateQuestion(@RequestBody ResponseVO<Question> questionResponseVO ){
        boolean update=questionService.updateById(questionResponseVO.getData());
        return update
                ?new ResponseVO<Boolean>(Status.SUCCESS,"update ok",update)
                :new ResponseVO<Boolean>(Status.ERROR,"update error",update);
    }


    @GetMapping("/{id}")  //查询一个问题
    ResponseVO<Question>getQuestion(@PathVariable  int id){          //查
        Question byId = questionService.getById(id);
        return byId !=null
                ?new ResponseVO<Question>(Status.SUCCESS,"get a question",byId)
                :new ResponseVO<Question>(Status.ERROR,"get failed",byId);
    }


    @GetMapping("/compute/{id}")    //根据问题id计算该问题的权重---热度
    ResponseVO<Boolean> computeWeighRatio(@PathVariable int id){
        Boolean aBoolean = questionService.computeWeighRatio(id);
        return aBoolean
                ?new ResponseVO<Boolean>(Status.SUCCESS,"compute successfully",aBoolean)
                :new ResponseVO<Boolean>(Status.ERROR,"compute weigh Ratio fail",aBoolean);
    }


    @GetMapping("/hotQuestion/{time}")   //根据权重返回降序后的文章集合
    ResponseVO<List<Article>> returnArticleToWebByweighRatio(@PathVariable String time){
        //“2023-6-28-17-51-23”   //查这天的所有文章，根据这天文章的权重返回排序后的文章集合
        List<Article> articles = questionService.returnQuestionToWebByWeighRatio(questionService.timeFormat(time));
        return articles != null
                ? new ResponseVO<List<Article>>(Status.SUCCESS, "desc successfully", articles)
                : new ResponseVO<List<Article>>(Status.ERROR, "desc error", articles);
    }



}
