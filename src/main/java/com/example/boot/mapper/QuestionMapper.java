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
    /**
     * 根据发布时间和权重降序模糊查询
     * @param time 时间
     * @return 返回降序后的所有问题
     */
    @Select("select * from tb_question where publish_date like #{time} order by weigh_ratio desc")
    public List<Question> getQuestionByTimeAndHot(@Param("time") String time);

    /**
     * 根据问题id更新该问题的权重
     * @param id 问题唯一标识
     * @param weigh_ratio 权重
     * @return 整数(更新成功返回1，失败返回0)
     */
    @Update("update tb_question set weigh_ratio=#{weigh_ratio} where id=#{id}")
    public Integer updateWeighRatio(@Param("id") int id, @Param("weigh_ratio") int weigh_ratio);

    /**
     * 根据发布时间升序查询问题
     * @return 升序查询后的所有问题
     */
    @Select("select * from tb_question order by publish_date asc")
    public List<Question> getQuestionByTimeAsc();

    /**
     * 根据用户id查询该用户发布的所有问题
     * @param userId 用户唯一标识
     * @return 返回所有问题
     */
    @Select("select * from tb_question where user_id =#{userId}")
    public List<Question> getQuestionById(int userId);

    /**
     * 根据权重降序查询问题
     * @return 降序查询后的所有问题
     */
    @Select("select *  from  tb_question order by weigh_ratio desc ")
    public List<Question> getQuestionIdQuestions();

}



