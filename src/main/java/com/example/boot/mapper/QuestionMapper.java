package com.example.boot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.boot.pojo.entity.Question;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface QuestionMapper extends BaseMapper<Question> {

    @Select("select * from tb_question where user_id =#{userId}")
    public List<Question> getQuestionById(int userId);

    @Select("select *  from  tb_question order by weigh_ratio desc ")
    public List<Question> getQuestionIdQuestions();

}



