package com.example.boot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.boot.pojo.entity.Article;
import com.example.boot.pojo.entity.Question;
import com.example.boot.pojo.vo.ResponseVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface QuestionMapper extends BaseMapper<Question> {

    @Select("select * from tb_question where publish_date like #{time} order by weigh_ratio desc")
    public List<Question> getQuestionByTimeAndHot(@Param("time") String time);
  
    public List<Article> getQuestionByTimeAndHot(@Param("time") String time);

    @Update("update tb_question set weigh_ratio=#{weigh_ratio} where id=#{id}")
    public Integer updateWeighRatio(@Param("id") int id, @Param("weigh_ratio") int weigh_ratio);

    @Select("select * from tb_question order by publish_date asc")
    public List<Question> getQuestionByTimeAsc();
  
    @Select("select * from tb_question where user_id =#{userId}")
    public List<Question> getQuestionById(int userId);

    @Select("select *  from  tb_question order by weigh_ratio desc ")
    public List<Question> getQuestionIdQuestions();

}



