package com.example.boot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.boot.pojo.entity.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ArticleMapper extends BaseMapper<Article> {

    List<Article> articleByHot();

    @Select("select  * from tb_question where user_id=#{userId}")
    List<Article> articleByUserId(Integer userId);

    @Select("select * from tb_article limit #{page}, #{pageSize}")
    List<Article> selectByMyPage (int page, int pageSize);

}
