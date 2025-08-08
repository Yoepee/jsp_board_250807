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
        return repository.getArticles().reversed();
    }
}
