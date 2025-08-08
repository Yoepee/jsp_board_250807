package com.back.jsp.board.boundedContext.base;

import com.back.jsp.board.boundedContext.article.controller.ArticleController;
import com.back.jsp.board.boundedContext.article.repository.ArticleRepository;
import com.back.jsp.board.boundedContext.article.service.ArticleService;
import com.back.jsp.board.boundedContext.member.controller.MemberController;

public class Container {
    public static ArticleRepository articleRepository;
    public static ArticleService articleService;
    public static MemberController memberController;
    public static ArticleController articleController;

    static {
        memberController = new MemberController();
        articleRepository = new ArticleRepository();
        articleService = new ArticleService();
        articleController = new ArticleController();
    }
}
