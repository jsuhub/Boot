package com.example.boot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.boot.pojo.entity.Article;
import com.example.boot.pojo.entity.Question;
import lombok.Data;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

import java.util.List;

@Mapper
public interface ArticleMapper extends BaseMapper<Article> {

    List<Article> articleByHot();

    @Select("select * from tb_article where tag like #{articleTag} ;")
    List<Article> articleByArticleTag(String articleTag);
  
    @Select("select * from tb_article where user_id=#{userId}")
    List<Article> articleByUserId(Integer userId);

    /**
     * 跟据文章id更新表的权重字段
     * @param id 唯一标识
     * @param weigh_ratio 文章权重
     * @return 整数(更新成功返回1，失败返回0)
     */
    @Update("update tb_article set weigh_ratio=#{weigh_ratio} where id=#{id}")
    public Integer updateWeighRatio(@Param("id") int id, @Param("weigh_ratio") int weigh_ratio);



    /**
     * 根据发布时间降序查询文章
     * @return 降序查询后的所有文章
     */
    @Select("select * from tb_article order by publish_date desc")
    public List<Article> getArticleByTimeDesc();

    @Select("select * from tb_article limit #{page}, #{pageSize}")
    List<Article> selectByMyPage (int page, int pageSize);
}
