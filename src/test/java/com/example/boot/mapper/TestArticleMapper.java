package com.example.boot.mapper;


import com.example.boot.pojo.entity.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestArticleMapper {

    @Autowired
    private ArticleMapper articleMapper;

    @Test
    void test() {
        Article article = new Article();
        article.setTitle("shuhao");
        articleMapper.insert(article);
    }

}
