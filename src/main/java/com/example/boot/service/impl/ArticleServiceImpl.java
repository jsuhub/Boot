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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements IArticleService{
  
    @Autowired
    ArticleMapper articleMapper;
  
    @Autowired
    FollowMapper followMapper;

    @Autowired
    QuestionMapper questionMapper;
  
    @Override
    public Boolean computeWeighRatio(int id) {   //前端传过来一个文章id，计算这篇文章的权重。并更新这篇文章的数据(加入权重字段)
        int weighRatio;
        Article article = articleMapper.selectById(id);
        Integer likeAmount = article.getLikeAmount();
        Integer starAmount = article.getStarAmount();
        Integer browserAmount = article.getBrowserAmount();
        weighRatio=likeAmount*3+starAmount*2+browserAmount;
        return articleMapper.updateWeighRatio(id,weighRatio) == 1
                ? true
                : false;
    }

    public List<Article> returnArticleToWebByweighRatio(String time){
        return articleMapper.getArticleByTimeAndHot(time);
    }

    public String timeFormat(String data){
        String time;
        String[] parts = data.split("-");//data="2023-6-29-1-29-45"
        int year = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int day = Integer.parseInt(parts[2]);
        time=year+"-"+month+"-"+day;
        return time+'%';
    }


    /**
     * 添加文章喜欢
     * @param id 文章Id
     * @return  文章喜欢量
     */
    public Integer articleLike( int id){
        Article article = articleMapper.selectById(id);
          int amount= article.getLikeAmount();
        amount++;
        article.setLikeAmount(amount);
        articleMapper.updateById(article);
        return amount;
    }

    /**
     * 添加文章收藏
     * @param id 文章Id
     * @return  文章收藏量
     */
    public Integer addStarAmount ( int id){
        Article article = articleMapper.selectById(id);
        int amount= article.getStarAmount();
        amount++;
        article.setStarAmount(amount);
        articleMapper.updateById(article);
        return amount;
    }

    /**
     * 添加游览量
     * @param id 文章Id
     * @return 浏览量
     */
    public Integer addBrowserAmount( int id){
        Article article = articleMapper.selectById(id);
        int amount= article.getBrowserAmount();
        amount++;
        article.setBrowserAmount(amount);
        articleMapper.updateById(article);
        return amount;
    }

    /**
     * 按照热度排序
     * @return 文章数组
     */
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

    /**
     * 取消点赞
     * @param id 文章ID
     * @return  点赞数量
     */
    public Integer  cancelArticle( int id)
    {
        Article article = articleMapper.selectById(id);
        int amount= article.getLikeAmount();
        amount--;
        article.setLikeAmount(amount);
        articleMapper.updateById(article);
        return amount;
    }

    /**
     * 取消收藏
     * @param id 文章Id
     * @return  收藏量
     */
    public Integer  cancelStarAmount( int id)
    {
        Article article = articleMapper.selectById(id);
        int amount= article.getStarAmount();
        amount--;
        article.setStarAmount(amount);
        articleMapper.updateById(article);
        return amount;
    }

    /**
     * 展现关注人发出的文章
     * @param userId 本地用户Id
     * @return 关注人的文章数组
     */
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
