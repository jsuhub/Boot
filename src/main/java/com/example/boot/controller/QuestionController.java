package com.example.boot.controller;
import com.example.boot.constant.Status;
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
                ?new ResponseVO<Question>(Status.SUCCESS,"get a question",byId)
                :new ResponseVO<Question>(Status.ERROR,"get failed",byId);
    }


    @GetMapping("/compute/{id}")    //根据问题的id计算该问题的权重---热度
    ResponseVO<Boolean> computeWeighRatio(@PathVariable int id){
        Boolean aBoolean = questionService.computeWeighRatio(id);
        return aBoolean
                ?new ResponseVO<Boolean>(Status.SUCCESS,"compute successfully",aBoolean)
                :new ResponseVO<Boolean>(Status.ERROR,"compute weigh Ratio fail",aBoolean);
    }


    @GetMapping("/hotQuestion/{time}")   //根据当天发布的问题和权重综合返回降序后的问题集合
    ResponseVO<List<Question>> returnQuestionToWebByWeighRatio(@PathVariable String time){
        //“2023-6-28-17-51-23”   //查这天的所有问题，根据这天问题的权重返回排序后的问题集合
        List<Question> question = questionService.returnQuestionToWebByWeighRatio(questionService.timeFormat(time));
        return question != null
                ? new ResponseVO<List<Question>>(Status.SUCCESS, "desc successfully",question)
                : new ResponseVO<List<Question>>(Status.ERROR, "desc error", question);
    }

    //根据时间降序返回问题集合
    @GetMapping("/timeQuestion")   //前端不需要传具体时间，直接返回当前库中按时间排序后的所有问题集合
    ResponseVO<List<Question>> returnQuestionByTimeAsc(){    //根据时间降序返回问题集合
        List<Question> question = questionService.returnQuestionByTimeAsc();
        return question!=null
                ?new ResponseVO<List<Question>>(Status.SUCCESS,"Asc successfully",question)
                :new ResponseVO<List<Question>>(Status.SUCCESS,"Asc successfully",question);
    }

}
