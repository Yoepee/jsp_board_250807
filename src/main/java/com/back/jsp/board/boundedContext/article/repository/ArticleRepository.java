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
        dbConnection = Container.dbConnection;
        findAll();
    }

    public List<Article> findAll() {
        articles = new ArrayList<>();
        List<Map<String, Object>> rows = dbConnection.selectRows("SELECT * FROM articles ORDER BY id DESC");
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
        } else {
            dbConnection.update("UPDATE articles SET title = '%s', content = '%s', count= '%s' WHERE id = %d".formatted(article.getTitle(), article.getContent(), article.getCount(), article.getId()));
            articles.removeIf(a -> a.getId() == article.getId());
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
