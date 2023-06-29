package com.example.boot.mapper;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.boot.pojo.entity.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class TestArticleMapper {

    @Autowired
    private ArticleMapper articleMapper;

    @Test
    void testSelectPage() {
        IPage iPage = new Page(0 ,5);
        IPage iPage1 = articleMapper.selectPage(iPage, null);
        System.out.println(iPage1);
//        articleMapper.selectPage(1, 2)
    }

    @Test
    void testSelectByMyPage() {
        List<Article> articleList = articleMapper.selectByMyPage(0, 10);
        System.out.println(articleList);
    }

}
