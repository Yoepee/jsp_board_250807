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
        List<Map<String, Object>> rows = dbConnection.selectRows("""
                SELECT * FROM articles 
                ORDER BY id DESC;
                """);
        System.out.println("ArticleRepository.findAll() rows = " + rows);
        for (Map<String, Object> row : rows) {
            Article article = new Article(row);
            articles.add(article);
        }

        return articles;
    }

    public Article saveArticle(Article article) {
        if (article.isNew()) {
            int id = dbConnection.insert("""
                    INSERT INTO articles (title, content, regDate) 
                    VALUES 
                    ('%s', '%s', NOW());
                    """.formatted(article.getTitle(), article.getContent()));
            article.setId(id);
            articles.add(article);
        } else {
            dbConnection.update("""
                    UPDATE articles 
                    SET title = '%s', content = '%s', count= '%s' 
                    WHERE id = %d;
                    """.formatted(article.getTitle(), article.getContent(), article.getCount(), article.getId()));
            findAll();
        }

        return article;
    }

    public boolean deleteArticle(long id) {
        dbConnection.delete("""
                DELETE FROM articles 
                WHERE id = %d;
                """.formatted(id));
        findAll();
        return true;
    }

    public Article findArticleById(long id) {
        Map<String, Object> row = dbConnection.selectRow("""
                SELECT * 
                FROM articles 
                WHERE id = %d;
                """.formatted(id));
        if (row == null) {
            return null;
        }
        return new Article(row);
    }
}
