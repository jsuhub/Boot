package com.example.boot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.boot.pojo.entity.Article;
import lombok.Data;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

import java.util.List;

@Mapper
public interface ArticleMapper extends BaseMapper<Article> {

    List<Article> articleByHot();

    @Select("select * from tb_article where tag  like #{articleTag};")
    List<Article> articleByArticleTag(String articleTag);
    @Select("select  * from tb_question where user_id=#{userId}")
    List<Article> articleByUserId(Integer userId);

    @Update("update tb_article set weigh_ratio=#{weigh_ratio} where id=#{id}")  //跟据文章id更新表数据(插入权重字段)
    public Integer updateWeighRatio(@Param("id") int id, @Param("weigh_ratio") int weigh_ratio);


    @Select("select * from tb_article where publish_date like #{time} order by weigh_ratio desc")  //根据当天所发布的文章的权重进行逆序
    public List<Article> getArticleByTimeAndHot(@Param("time") String time);   //返回排序后的文章集合 time="2023-6-29-10-11-11"

    @Select("select * from tb_article limit #{page}, #{pageSize}")
    List<Article> selectByMyPage (int page, int pageSize);
}
