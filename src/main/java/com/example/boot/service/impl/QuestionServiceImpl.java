package com.example.boot.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.boot.mapper.FollowMapper;
import com.example.boot.mapper.QuestionMapper;
import com.example.boot.pojo.entity.Article;
import com.example.boot.pojo.entity.Follow;
import com.example.boot.pojo.entity.Question;
import com.example.boot.service.IQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question> implements IQuestionService {
  
    @Autowired
    QuestionMapper questionMapper;
  
    @Override
    public Boolean computeWeighRatio(int id) {   //前端传过来一个文章id，计算这篇文章的权重。并更新这篇文章的数据(加入权重字段)
        int weighRatio;
        Question question = questionMapper.selectById(id);
        Integer likeAmount = question.getLikeAmount();
        Integer browserAmount = question.getBrowserAmount();
        weighRatio=likeAmount*3+browserAmount;
        return questionMapper.updateWeighRatio(id,weighRatio) == 1
                ? true
                : false;
    }


    public List<Article> returnQuestionToWebByWeighRatio(String time){
        return questionMapper.getQuestionByTimeAndHot(time);
    }
  
    @Autowired
    FollowMapper followMapper;
  
    @Autowired
    QuestionMapper questionMapper;
  
    //展示关注的用户上传的问题（上传自己id）
    public List<Question> showProblem(int userId) {
        List<Follow> follows = followMapper.showUser(userId);
        List<Question> questions=new ArrayList<>();
        for (int i = 0; i < follows.size(); i++)
        {
            List<Question> questionById = questionMapper.getQuestionById(follows.get(i).getUser1Id());
            for (int j=0;j<questionById.size();j++)
            {
                questions.add(questionById.get(j));
            }
        }
        return questions;
    }

    //展现热点问题
    public List<Question> questionsListByHot()
    {
        Wrapper<Question> wrapper = null;
        questionMapper.selectList(wrapper);
        List<Question>  questions = questionMapper.getQuestionIdQuestions();
        for ( int i=0;i<questions.size();i++)
        {
            int hot= questions.get(i).getBrowserAmount()+
                    questions.get(i).getLikeAmount()*5;
            questions.get(i).setHot(hot);
            questionMapper.updateById(questions.get(i));   }
        return questions;
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
}
