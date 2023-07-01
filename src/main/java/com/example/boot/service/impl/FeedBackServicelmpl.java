package com.example.boot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.boot.mapper.CommentMapper;
import com.example.boot.mapper.FeedBackMapper;
import com.example.boot.pojo.entity.Comment;
import com.example.boot.pojo.entity.FeedBack;
import com.example.boot.service.ICommentService;
import com.example.boot.service.IFeedBackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedBackServicelmpl  extends ServiceImpl<FeedBackMapper, FeedBack> implements IFeedBackService {

}
