package com.back.jsp.board.boundedContext.article.repository;

import com.back.jsp.board.boundedContext.article.dto.ArticleDto;
import com.back.jsp.board.boundedContext.article.entity.Article;
import com.back.jsp.board.boundedContext.base.Container;
import com.back.jsp.board.db.DBConnection;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
public class ArticleRepository {
    private DBConnection dbConnection;

    public ArticleRepository() {
        dbConnection = Container.dbConnection;
    }

    public List<Article> findAll() {
        List<Article> articles = new ArrayList<>();
        List<Map<String, Object>> rows = dbConnection.selectRows("""
                SELECT *
                FROM articles
                ORDER BY id DESC;
                """);
        for (Map<String, Object> row : rows) {
            Article article = new Article(row);
            articles.add(article);
        }

        return articles;
    }

    public List<ArticleDto> findJoinedMemberAll() {
        List<ArticleDto> articleDtos = new ArrayList<>();
        List<Map<String, Object>> rows = dbConnection.selectRows("""
                SELECT a.*, m.name AS author_name
                FROM articles AS a
                INNER JOIN members AS m
                ON a.author_id = m.id
                ORDER BY id DESC;
                """);
        for (Map<String, Object> row : rows) {
            ArticleDto articleDto = new ArticleDto(row);
            articleDtos.add(articleDto);
        }

        return articleDtos;
    }

    public Article findArticleById(long id) {
        Map<String, Object> row = dbConnection.selectRow("""
                SELECT *
                FROM articlee
                WHERE a.id = %d;
                """.formatted(id));
        if (row.isEmpty()) {
            return null;
        }
        return new Article(row);
    }

    public ArticleDto findJoinedMemberById(long id) {
        Map<String, Object> row = dbConnection.selectRow("""
                SELECT a.*, m.name AS author_name
                FROM articles AS a
                INNER JOIN members AS m
                ON a.author_id = m.id
                WHERE a.id = %d;
                """.formatted(id));
        if (row.isEmpty()) {
            return null;
        }
        return new ArticleDto(row);
    }

    public Article saveArticle(Article article) {
        if (article.isNew()) {
            int id = dbConnection.insert("""
                    INSERT INTO articles (title, content, author_id, regDate) 
                    VALUES 
                    ('%s', '%s', %d,NOW());
                    """.formatted(article.getTitle(), article.getContent(), article.getAuthorId()));
            article.setId(id);
        } else {
            dbConnection.update("""
                    UPDATE articles 
                    SET title = '%s', content = '%s', count= '%s' 
                    WHERE id = %d;
                    """.formatted(article.getTitle(), article.getContent(), article.getCount(), article.getId()));
        }

        return article;
    }

    public boolean deleteArticle(Article article) {
        dbConnection.delete("""
                DELETE FROM articles 
                WHERE id = %d;
                """.formatted(article.getId()));
        return true;
    }
}
