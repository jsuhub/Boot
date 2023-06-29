package com.example.boot.controller;

import com.example.boot.pojo.entity.Article;
import com.example.boot.pojo.entity.Comment;
import com.example.boot.pojo.vo.RequestVO;
import com.example.boot.pojo.vo.ResponseVO;
import com.example.boot.constant.Status;
import com.example.boot.service.impl.ArticleServiceImpl;
import com.example.boot.service.impl.CommentServicelmpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentServicelmpl commentService;

    @PostMapping
    ResponseVO<Boolean> saveComment(@RequestBody RequestVO<Comment> commentRequestVO) {
        boolean save = commentService.save(commentRequestVO.getData());
        return save
                ? new ResponseVO<Boolean>(Status.SUCCESS, "save ok", save)
                : new ResponseVO<Boolean>(Status.ERROR, "save error", save);
    }

    @DeleteMapping("/{id}")
    ResponseVO<Boolean> removeCommentById(@PathVariable int id) {
        System.out.println(id);
        boolean remove = commentService.removeById(id);
        return remove
                ? new ResponseVO<Boolean>(Status.SUCCESS, "remove ok", remove)
                : new ResponseVO<Boolean>(Status.ERROR, "remove ok", remove);
    }


    @GetMapping("/{id}")
    ResponseVO<Comment> getCommentById(@PathVariable int id) {
        Comment comment = commentService.getById(id);
        return comment != null
                ? new ResponseVO<Comment>(Status.SUCCESS, "get a user", comment)
                : new ResponseVO<Comment>(Status.ERROR, "get a user", comment);
    }

    @GetMapping("/list")
    ResponseVO<List<Comment>> listComment() {
        List<Comment> commentsleList = commentService.list();
        return commentsleList != null
                ? new ResponseVO<List<Comment>>(Status.SUCCESS, "list a user",commentsleList )
                : new ResponseVO<List<Comment>>(Status.ERROR, "list a user", commentsleList);
    }

    @GetMapping("/atrticle/{id}")
    ResponseVO<List<Comment>> listArticleComment(@PathVariable int id) {
        List<Comment> commentsleList = commentService.selectListCommentByArticleId(id);
        return commentsleList != null
                ? new ResponseVO<List<Comment>>(Status.SUCCESS, "list a user",commentsleList )
                : new ResponseVO<List<Comment>>(Status.ERROR, "list a user", commentsleList);
    }

    @GetMapping("/question/{id}")
    ResponseVO<List<Comment>> listQuestionComment(@PathVariable int id) {
        List<Comment> commentsleList = commentService.selectListCommentByQuestionId(id);
        return commentsleList != null
                ? new ResponseVO<List<Comment>>(Status.SUCCESS, "list a user",commentsleList )
                : new ResponseVO<List<Comment>>(Status.ERROR, "list a user", commentsleList);
    }
}
