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

@CrossOrigin
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

    /**
     *
     * @param id
     * @return
     * @author yuliang
     */
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
                ?new ResponseVO<>(Status.SUCCESS,"get a question",byId)
                :new ResponseVO<>(Status.ERROR,"get failed",byId);
    }

    @GetMapping("/compute/{id}")    //根据问题的id计算该问题的权重---热度
    ResponseVO<Boolean> computeWeighRatio(@PathVariable int id){
        Boolean aBoolean = questionService.computeWeighRatio(id);
        return aBoolean
                ?new ResponseVO<>(Status.SUCCESS,"compute successfully",aBoolean)
                :new ResponseVO<>(Status.ERROR,"compute weigh Ratio fail",aBoolean);
    }

    //根据时间降序返回问题集合
    @GetMapping("/timeQuestion")   //前端不需要传具体时间，直接返回当前库中按时间排序后的所有问题集合
    ResponseVO<List<Question>> returnQuestionByTimeAsc(){    //根据时间降序返回问题集合
        List<Question> question = questionService.returnQuestionByTimeAsc();
        return question!=null
                ?new ResponseVO<>(Status.SUCCESS,"Asc successfully",question)
                :new ResponseVO<>(Status.SUCCESS,"Asc successfully",question);
    }

    @GetMapping("/hotQuestion/{time}")   //根据权重返回降序后的文章集合
    ResponseVO<List<Question>> returnArticleToWebByweighRatio(@PathVariable String time){
        //“2023-6-28-17-51-23”   //查这天的所有文章，根据这天文章的权重返回排序后的文章集合
        List<Question> questions = questionService.returnQuestionToWebByWeighRatio(questionService.timeFormat(time));
        return questions != null
                ? new ResponseVO<>(Status.SUCCESS, "desc successfully", questions)
                : new ResponseVO<>(Status.ERROR, "desc error", questions);
    }

}
