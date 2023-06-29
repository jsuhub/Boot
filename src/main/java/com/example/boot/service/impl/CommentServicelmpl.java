package com.example.boot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.boot.mapper.CommentMapper;
import com.example.boot.pojo.entity.Comment;
import com.example.boot.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServicelmpl extends ServiceImpl<CommentMapper,Comment> implements ICommentService {
    @Autowired
    private CommentMapper commentMapper;

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
}
