package com.example.boot.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.boot.constant.Weight;
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

    /**
     * 根据文章id计算该文章热度
     *
     * @param id
     * @return
     */
    @Override
    public Boolean computeHot(int id) {
        Question question = questionMapper.selectById(id);
        Integer likeAmount = question.getLikeAmount();
        Integer browserAmount = question.getBrowserAmount();
        Integer hot = likeAmount * Weight.LIKE + browserAmount * Weight.BROWSER;
        question.setHot(hot);
        int i = questionMapper.updateById(question);
        return i > 0
                ? true
                : false;
    }

}
