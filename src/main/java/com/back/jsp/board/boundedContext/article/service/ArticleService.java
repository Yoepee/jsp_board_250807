package com.back.jsp.board.boundedContext.article.service;

import com.back.jsp.board.boundedContext.article.entity.Article;
import com.back.jsp.board.boundedContext.article.repository.ArticleRepository;
import com.back.jsp.board.boundedContext.base.Container;

import java.util.List;

public class ArticleService {
    private ArticleRepository repository;

    public ArticleService() {
        this.repository = Container.articleRepository;
    }

    public List<Article> getArticles() {
        return repository.findAll();
    }

    public Article writeArticle(String title, String content, long authorId) {
        return repository.saveArticle(new Article(title, content, authorId));
    }
    public Article modifyArticle(Article article, String title, String content) {
        article.setTitle(title);
        article.setContent(content);
        return repository.saveArticle(article);
    }
    public Article getArticleById(long id) {
        Article article = repository.findArticleById(id);
        if (article == null) {
            return null;
        }
        return article;
    }
    public Article getDetailById(long id) {
        Article article = getArticleById(id);
        if (article == null) {
            return null;
        }
        article.increaseCount();
        repository.saveArticle(article);
        return article;
    }
    public boolean deleteArticle(long id) {
        Article article = getArticleById(id);
        if (article == null) {
            return false;
        }
        return repository.deleteArticle(id);
    }
}
