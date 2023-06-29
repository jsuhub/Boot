package com.example.boot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.boot.pojo.entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CommentMapper extends BaseMapper<Comment> {

    @Select("select * from tb_comment_essay where user_id=#{userId} and essay_id=#{essayId}")
       public Comment selectCommentByUserIdandEssayId(int userId, int essayId);
}
