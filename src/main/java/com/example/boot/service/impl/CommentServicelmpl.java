package com.example.boot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.boot.mapper.CommentMapper;
import com.example.boot.pojo.entity.Comment;
import com.example.boot.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServicelmpl extends ServiceImpl<CommentMapper,Comment> implements ICommentService {
    @Autowired
    private CommentMapper commentMapper;

    /**
     * 添加问题评论
     * @param comment 一个评论
     * @return 成功/失败
     */
    public Boolean addComment_ques(Comment comment){
        Integer essayId = comment.getEssayId();
        Integer userId = comment.getUserId();
        Comment comment1=commentMapper.selectCommentByUserIdandEssayId(userId,essayId);
        if(comment1 ==null)
        {
            commentMapper.insert(comment);
            return true;
        }
        else return false;
    }

    /**第一条是热度最高的评论，其他按照时间顺序排列
     *  输入用户文章id
     * @param id 表示传入文章id
     * @return  评论数组
     */
    public List<Comment> selectListCommentByArticleId(int id)
    {
        Comment comment=commentMapper.selectCommentByBest(id);
        List<Comment> comments = commentMapper.selectCommentByEssayId(id,comment.getId());
        comments.add(0,comment);
        return comments;
    }

    /**
     * 第一条是热度最高的评论对问题，其他按照时间顺序排列
     * @param id 问题id
     * @return 一系列评论
     */
    public List<Comment> selectListCommentByQuestionId(int id)
    {
        Comment comment=commentMapper.selectProblemCommentByBest(id);
        List<Comment> comments = commentMapper.selectCommentByQuestion(id,comment.getId());
        comments.add(0,comment);
        return comments;
    }
}
