package com.back.jsp.board.boundedContext.article.repository;

import com.back.jsp.board.boundedContext.article.entity.Article;
import com.back.jsp.board.boundedContext.base.Container;
import com.back.jsp.board.db.DBConnection;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
public class ArticleRepository {
    private List<Article> articles;
    private DBConnection dbConnection;

    public ArticleRepository() {
        articles = new ArrayList<>();

        dbConnection = Container.dbConnection;
        LoadDBRows();
    }

    private List<Article> LoadDBRows() {
        List<Map<String, Object>> rows = dbConnection.selectRows("SELECT * FROM articles");
        for (Map<String, Object> row : rows) {
            Article article = new Article(row);
            articles.add(article);
        }

        return articles;
    }

    public Article saveArticle(Article article) {
        if (article.isNew()) {
            int id = dbConnection.insert("INSERT INTO articles (title, content) VALUES ('%s', '%s')".formatted(article.getTitle(), article.getContent()));
            article.setId(id);
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
