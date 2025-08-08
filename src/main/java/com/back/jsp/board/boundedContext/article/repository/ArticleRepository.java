package com.back.jsp.board.boundedContext.article.repository;

import com.back.jsp.board.boundedContext.article.entity.Article;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.LongStream;

@Getter
public class ArticleRepository {
    private List<Article> articles;
    private int lastId;

    public ArticleRepository() {
        articles = new ArrayList<>();
        lastId = 0;
        LongStream.rangeClosed(1,5).forEach(
            i -> saveArticle(new Article("제목" + i, "내용" + i))
        );
    }

    public Article saveArticle(Article article) {
        if (article.isNew()) {
            article.setId(++lastId);
            articles.add(article);
        }

        return article;
    }

    public Article getArticleById(int id) {
        return articles.stream()
                .filter(article -> article.getId() == id)
                .findFirst()
                .orElse(null);
    }
}
