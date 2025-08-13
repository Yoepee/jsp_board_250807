package com.back.jsp.board.boundedContext.base;

import com.back.jsp.board.boundedContext.article.controller.ArticleController;
import com.back.jsp.board.boundedContext.article.repository.ArticleRepository;
import com.back.jsp.board.boundedContext.article.service.ArticleService;
import com.back.jsp.board.boundedContext.home.controller.HomeController;
import com.back.jsp.board.boundedContext.member.controller.MemberController;
import com.back.jsp.board.boundedContext.member.repository.MemberRepository;
import com.back.jsp.board.boundedContext.member.service.MemberService;
import com.back.jsp.board.db.DBConnection;

public class Container {
    public static MemberRepository memberRepository;
    public static MemberService memberService;
    public static MemberController memberController;
    public static HomeController homeController;
    public static ArticleRepository articleRepository;
    public static ArticleService articleService;
    public static ArticleController articleController;
    public static DBConnection dbConnection;

    static {
        dbConnection = new DBConnection();
        memberRepository = new MemberRepository();
        memberService = new MemberService();
        memberController = new MemberController();
        articleRepository = new ArticleRepository();
        articleService = new ArticleService();
        articleController = new ArticleController();
        homeController = new HomeController();
    }
}
