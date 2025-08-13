package com.back.jsp.board.boundedContext.article.service;

import com.back.jsp.board.boundedContext.article.dto.ArticleDto;
import com.back.jsp.board.boundedContext.article.entity.Article;
import com.back.jsp.board.boundedContext.article.repository.ArticleRepository;
import com.back.jsp.board.boundedContext.base.Container;
import com.back.jsp.board.boundedContext.member.entity.Member;

import java.util.List;

public class ArticleService {
    private ArticleRepository repository;

    public ArticleService() {
        this.repository = Container.articleRepository;
    }

    public List<Article> getArticles() {
        return repository.findAll();
    }
    public List<ArticleDto> findJoinedMemberAll() {
        return repository.findJoinedMemberAll();
    }

    public Article writeArticle(String title, String content, Member member) {
        return repository.saveArticle(new Article(title, content, member.getId()));
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
    public ArticleDto getJoinedMemberById(long id) {
        ArticleDto articleDto = repository.findJoinedMemberById(id);
        if (articleDto == null) {
            return null;
        }
        articleDto.increaseCount();
        repository.saveArticle(new Article(articleDto));
        return articleDto;
    }
    public boolean deleteArticle(Article article) {
        return repository.deleteArticle(article);
    }
}
