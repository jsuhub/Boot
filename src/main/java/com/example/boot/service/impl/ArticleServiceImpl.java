package com.example.boot.service.impl;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.boot.mapper.ArticleMapper;
import com.example.boot.mapper.FollowMapper;
import com.example.boot.mapper.QuestionMapper;
import com.example.boot.pojo.entity.Article;
import com.example.boot.pojo.entity.Follow;
import com.example.boot.pojo.entity.Question;
import com.example.boot.service.IArticleService;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements IArticleService{

    @Autowired
    ArticleMapper articleMapper;
    @Autowired
    FollowMapper followMapper;

    @Autowired
    QuestionMapper questionMapper;
    //添加喜欢
    public Integer articleLike( int id){
        Article article = articleMapper.selectById(id);
          int amount= article.getLikeAmount();
        amount++;
        article.setLikeAmount(amount);
        articleMapper.updateById(article);
        return amount;
    }
//添加收藏
    public Integer addStarAmount ( int id){
        Article article = articleMapper.selectById(id);
        int amount= article.getStarAmount();
        amount++;
        article.setStarAmount(amount);
        articleMapper.updateById(article);
        return amount;
    }
    //添加游览量
    public Integer addBrowserAmount( int id){
        Article article = articleMapper.selectById(id);
        int amount= article.getBrowserAmount();
        amount++;
        article.setBrowserAmount(amount);
        articleMapper.updateById(article);
        return amount;
    }
    //按照热度排序
    public List<Article> articleListByHot()
    {
        Wrapper<Article> wrapper = null;
        articleMapper.selectList(wrapper);
        List<Article> articles = articleMapper.articleByHot();
        for ( int i=0;i<articles.size();i++)
        {
         int hot= articles.get(i).getBrowserAmount()+
            articles.get(i).getLikeAmount()*5+
            articles.get(i).getStarAmount()*10;
            articles.get(i).setHot(hot);
            articleMapper.updateById(articles.get(i));   }
            return articles;
    }

    //取消喜欢
    public Integer  cancelArticle( int id)
    {
        Article article = articleMapper.selectById(id);
        int amount= article.getLikeAmount();
        amount--;
        article.setLikeAmount(amount);
        articleMapper.updateById(article);
        return amount;
    }
    //取消收藏

    public Integer  cancelStarAmount( int id)
    {
        Article article = articleMapper.selectById(id);
        int amount= article.getStarAmount();
        amount--;
        article.setStarAmount(amount);
        articleMapper.updateById(article);
        return amount;
    }

//展现关注人的发出的文章
    public  List<Article> showArticle(int userId) {
        List<Follow> follows = followMapper.showUser(userId);
        List<Article>  articles=null;
        for (int i = 0; i < follows.size(); i++)
        {
            List<Article> articles1 = articleMapper.articleByUserId(follows.get(i).getUserId());
               for (int j=0;j<articles1.size();j++)
               {
                articles.add(articles1.get(j));
               }
        }
        return articles;
    }






}
