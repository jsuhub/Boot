package com.example.boot.service.impl;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.boot.mapper.ArticleMapper;
import com.example.boot.mapper.FollowMapper;
import com.example.boot.mapper.QuestionMapper;
import com.example.boot.pojo.entity.Article;
import com.example.boot.pojo.entity.Follow;
import com.example.boot.pojo.entity.Question;
import com.example.boot.service.IArticleService;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements IArticleService {

    @Autowired
    ArticleMapper articleMapper;

    @Autowired
    FollowMapper followMapper;

    @Autowired
    QuestionMapper questionMapper;

    /**
     * 根据文章id计算该文章权重，并更新权重字段数据
     *
     * @param id 文章唯一标识
     * @return 返回一个boolean值(true标识更新权重字段成功 ， false表示更新失败)
     */
    @Override
    public Boolean computeWeighRatio(int id) {
        int weighRatio;
        Article article = articleMapper.selectById(id);
        Integer likeAmount = article.getLikeAmount();
        Integer starAmount = article.getStarAmount();
        Integer browserAmount = article.getBrowserAmount();
        weighRatio = likeAmount * 3 + starAmount * 2 + browserAmount;
        return articleMapper.updateWeighRatio(id, weighRatio) == 1
                ? true
                : false;
    }

    /**
     * 返回根据时间和权重排序后的所有文章
     *
     * @param time 时间
     * @return 返回排序后的所有文章
     */
    public List<Article> returnArticleToWebByweighRatio(String time, int page, int size) {
        Page<Article> articlePage = new Page<>(page, size);
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        //@Select("select * from tb_article where publish_date like #{time} order by weigh_ratio desc")
        queryWrapper.like("publish_date", time);
        queryWrapper.orderByDesc("weigh_ratio");
        IPage<Article> page1 = articleMapper.selectPage(articlePage, queryWrapper);
        return page1.getRecords();
    }

    /**
     * 对时间进行分割，保留时间的年月日
     *
     * @param data 时间
     * @return 返回修改后的时间
     */
    public String timeFormat(String data) {
        String time;
        String[] parts = data.split("-");
        int year = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int day = Integer.parseInt(parts[2]);
        time = year + "-" + month + "-" + day;
        return time + '%';
    }

    /**
     * 返回根据时间降序后的所有文章
     *
     * @return 降序后的所有文章
     */
    public List<Article> returnArticleByTimeDesc() {
        return articleMapper.getArticleByTimeDesc();
    }





    /**
     * 按照热度排序
     *
     * @return 文章数组
     */
    public List<Article> articleListByHot() {
        Wrapper<Article> wrapper = null;
        articleMapper.selectList(wrapper);
        List<Article> articles = articleMapper.articleByHot();
        for (int i = 0; i < articles.size(); i++) {
            int hot = articles.get(i).getBrowserAmount() +
                    articles.get(i).getLikeAmount() * 5 +
                    articles.get(i).getStarAmount() * 10;
            articles.get(i).setHot(hot);
            articleMapper.updateById(articles.get(i));
        }
        return articles;
    }

    /**
     * 分野查询热门文章
     *
     * @param page
     * @param size
     * @return
     */
    public List<Article> articleListByHotPage(int page, int size) {

        /**
         * 追加 limit 0，5 到SQL去，但是默认不开启，要通过配置MP拦截器
         *
         */


        List<Article> articles = articleMapper.articleByHot();
        return articles;
    }

    /**
     * 取消点赞
     *
     * @param id 文章ID
     * @return 点赞数量
     */
    public Integer cancelArticle(int id) {
        Article article = articleMapper.selectById(id);
        int amount = article.getLikeAmount();
        amount--;
        article.setLikeAmount(amount);
        articleMapper.updateById(article);
        return amount;
    }


    /**
     * 取消收藏
     *
     * @param id 文章Id
     * @return 收藏量
     */
    public Integer cancelStarAmount(int id) {
        Article article = articleMapper.selectById(id);
        int amount = article.getStarAmount();
        amount--;
        article.setStarAmount(amount);
        articleMapper.updateById(article);
        return amount;
    }

}