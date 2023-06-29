package com.example.boot.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.boot.mapper.FollowMapper;
import com.example.boot.mapper.QuestionMapper;
import com.example.boot.pojo.entity.Article;
import com.example.boot.pojo.entity.Follow;
import com.example.boot.pojo.entity.Question;
import com.example.boot.pojo.vo.ResponseVO;
import com.example.boot.service.IQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question> implements IQuestionService {
  
    @Autowired
    QuestionMapper questionMapper;

    FollowMapper followMapper;

    /**
     * 根据问题的id计算该问题权重，并更新权重字段数据
     * @param id 问题唯一标识
     * @return 返回boolean值(true标识更新权重字段成功，false表示更新失败)
     */
    @Override
    public Boolean computeWeighRatio(int id) {
        int weighRatio;
        Question question = questionMapper.selectById(id);
        Integer likeAmount = question.getLikeAmount();
        Integer browserAmount = question.getBrowserAmount();
        weighRatio=likeAmount*3+browserAmount;
        return questionMapper.updateWeighRatio(id,weighRatio) == 1
                ? true
                : false;
    }

    /**
     * 返回根据时间和权重排序后的所有问题
     * @param time 时间
     * @return 返回排序后的所有问题
     */
    public List<Question> returnQuestionToWebByWeighRatio(String time){
        return questionMapper.getQuestionByTimeAndHot(time);
    }

    /**
     * 对时间进行分割，保留时间的年月日
     * @param data 时间
     * @return 返回修改后的时间
     */
    public String timeFormat(String data){
        String time;
        String[] parts = data.split("-");//data="2023-6-29-1-29-45"
        int year = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int day = Integer.parseInt(parts[2]);
        time=year+"-"+month+"-"+day;
        return time+'%';
    }

    /**
     * 返回根据时间降序后的所有问题
     * @return 降序后的所有问题
     */
    public List<Question> returnQuestionByTimeAsc(){
        return questionMapper.getQuestionByTimeAsc();
    }

    /**
     * 展示关注用户的问题
     * @param userId 用户id
     * @return 一系列关注问题
     */
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

}
