package com.example.boot.controller;
import com.example.boot.constant.Status;
import com.example.boot.pojo.entity.Article;
import com.example.boot.pojo.entity.Question;
import com.example.boot.pojo.vo.ResponseVO;
import com.example.boot.service.impl.QuestionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import java.util.List;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    @Autowired
    private QuestionServiceImpl questionService;

    /**
     * 新增一个问题
     * @param questionResponseVO 请求数据实体
     * @return ResponseVO<Boolean> 响应数据实体
     */
    @PostMapping
    ResponseVO<Boolean> saveQuestion(@RequestBody ResponseVO<Question> questionResponseVO){
        boolean save=questionService.save(questionResponseVO.getData());
        return save
                ? new ResponseVO<Boolean>(Status.SUCCESS,"save ok",save)
                : new ResponseVO<Boolean>(Status.ERROR,"save error",save);
    }

    /**
     * 根据问题的id删除这个问题
     * @param id 问题的唯一标识
     * @return ResponseVO<Boolean> 响应数据实体
     */
    @DeleteMapping("/{id}")
    ResponseVO<Boolean>removeQuestion(@PathVariable int id){
        boolean remove=questionService.removeById(id);
        return remove
                ?new ResponseVO<Boolean>(Status.SUCCESS,"remove ok",remove)
                :new ResponseVO<>(Status.ERROR,"remove error",remove);
    }

    /**
     * 对一个问题的内容进行更改
     * @param questionResponseVO 请求数据实体
     * @return ResponseVO<Boolean> 响应数据实体
     */
    @PutMapping
    ResponseVO<Boolean> updateQuestion(@RequestBody ResponseVO<Question> questionResponseVO ){
        boolean update=questionService.updateById(questionResponseVO.getData());
        return update
                ?new ResponseVO<Boolean>(Status.SUCCESS,"update ok",update)
                :new ResponseVO<Boolean>(Status.ERROR,"update error",update);
    }

    /**
     * 根据id查询一个问题
     * @param id 问题的唯一标识
     * @return ResponseVO<Question> 响应数据实体
     */
    @GetMapping("/{id}")
    ResponseVO<Question>getQuestion(@PathVariable  int id){
        Question byId = questionService.getById(id);
        return byId !=null
                ?new ResponseVO<>(Status.SUCCESS,"get a question",byId)
                :new ResponseVO<>(Status.ERROR,"get failed",byId);
    }

    /**
     * 根据问题id计算该问题的权重(热度)
     * @param id 问题的唯一标识
     * @return ResponseVO<Boolean> 响应数据实体
     */
    @GetMapping("/compute/{id}")
    ResponseVO<Boolean> computeWeighRatio(@PathVariable int id){
        Boolean aBoolean = questionService.computeWeighRatio(id);
        return aBoolean
                ?new ResponseVO<>(Status.SUCCESS,"compute successfully",aBoolean)
                :new ResponseVO<>(Status.ERROR,"compute weigh Ratio fail",aBoolean);
    }

    /**
     * 根据时间降序返回当前库中所有的问题
     * @return ResponseVO<List<Question>> 响应数据实体
     */
    @GetMapping("/timeQuestion")
    ResponseVO<List<Question>> returnQuestionByTimeAsc(){
        List<Question> question = questionService.returnQuestionByTimeAsc();
        return question!=null
                ?new ResponseVO<>(Status.SUCCESS,"Asc successfully",question)
                :new ResponseVO<>(Status.SUCCESS,"Asc successfully",question);
    }

    /**
     * 根据当天发布的问题的权重返回降序后的所有问题
     * @param time 当天的时间
     * @return ResponseVO<List<Question>> 响应数据实体
     */
    @GetMapping("/hotQuestion/{time}")
    ResponseVO<List<Question>> returnQuestionToWebByWeighRatio(@PathVariable String time){
        List<Question> questions = questionService.returnQuestionToWebByWeighRatio(questionService.timeFormat(time));
        return questions != null
                ? new ResponseVO<>(Status.SUCCESS, "desc successfully", questions)
                : new ResponseVO<>(Status.ERROR, "desc error", questions);
    }

}
