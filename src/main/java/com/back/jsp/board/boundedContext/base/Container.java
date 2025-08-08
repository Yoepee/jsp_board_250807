package com.back.jsp.board.boundedContext.base;

import com.back.jsp.board.boundedContext.article.controller.ArticleController;
import com.back.jsp.board.boundedContext.member.controller.MemberController;

public class Container {
    public static MemberController memberController;
    public static ArticleController articleController;

    static {
        memberController = new MemberController();
        articleController = new ArticleController();
    }
}
