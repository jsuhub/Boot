package com.example.boot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.boot.mapper.QuestionMapper;
import com.example.boot.pojo.entity.Question;
import com.example.boot.pojo.vo.ResponseVO;
import com.example.boot.service.IQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question> implements IQuestionService {
    @Autowired
    QuestionMapper questionMapper;


    public Boolean computeWeighRatio(int id) {   //前端传过来一问题id，计算这篇问题的权重。并更新这篇文章的数据(加入权重字段)
        int weighRatio;
        Question question = questionMapper.selectById(id);
        Integer likeAmount = question.getLikeAmount();
        Integer browserAmount = question.getBrowserAmount();
        weighRatio=likeAmount*3+browserAmount;
        return questionMapper.updateWeighRatio(id,weighRatio) == 1
                ? true
                : false;
    }


    public List<Question> returnQuestionToWebByWeighRatio(String time){
        return questionMapper.getQuestionByTimeAndHot(time);
    }


    public String timeFormat(String data){
        String time;
        String[] parts = data.split("-");//data="2023-6-29-1-29-45"
        int year = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int day = Integer.parseInt(parts[2]);
        time=year+"-"+month+"-"+day;
        return time+'%';
    }


    public List<Question> returnQuestionByTimeAsc(){    //根据时间降序返回问题集合
        return questionMapper.getQuestionByTimeAsc();
    }


}
