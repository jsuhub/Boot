package com.example.boot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.boot.pojo.entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommentMapper extends BaseMapper<Comment> {

    @Select("select * from tb_comment_essay where user_id=#{userId} and essay_id=#{essayId}")
    public Comment selectCommentByUserIdandEssayId(int userId, int essayId);
  
    @Select("select * from tb_comment_essay where  essay_id=#{essayId} and id<>#{hotEssayId} ORDER BY date DESC")
    public List<Comment> selectCommentByEssayId(int essayId, int hotEssayId);

    @Select("select * from tb_comment_essay  where essay_id=#{essayId} order by like_amount DESC limit 1")
    public Comment selectCommentByBest(int Id);
  
    @Select("select * from tb_comment_question where  prob_id=#{probId} and id<>#{hotProbId} ORDER BY date DESC")
    public List<Comment> selectCommentByQuestion(int probId, int hotProbId);

    @Select("select * from tb_comment_question  where prob_id=#{probId} order by like_amount DESC limit 1")
    public Comment selectProblemCommentByBest(int Id);
}

