package com.back.jsp.board.boundedContext.article.repository;

import com.back.jsp.board.boundedContext.article.entity.Article;
import com.back.jsp.board.boundedContext.base.Container;
import com.back.jsp.board.db.DBConnection;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.LongStream;

@Getter
public class ArticleRepository {
    private List<Article> articles;
    private int lastId;
    private DBConnection dbConnection;

    public ArticleRepository() {
        articles = new ArrayList<>();
        lastId = 0;
        makeTestData();

        dbConnection = Container.dbConnection;
        dbConnection.connect();
    }

    private void makeTestData() {
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

    public boolean deleteArticle(Article article) {
        articles.remove(article);
        return true;
    }

    public Article getArticleById(long id) {
        return articles.stream()
                .filter(article -> article.getId() == id)
                .findFirst()
                .orElse(null);
    }
}
