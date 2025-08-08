package com.back.jsp.board.boundedContext.article.controller;

import com.back.jsp.board.boundedContext.article.service.ArticleService;
import com.back.jsp.board.boundedContext.base.Container;
import com.back.jsp.board.boundedContext.global.base.Rq;

public class ArticleController {
    private ArticleService articleService;

    public ArticleController() {
        this.articleService = Container.articleService;
    }
    public void showList(Rq rq) {
        rq.setAttr("articles", articleService.getArticles());
        rq.view("usr/article/list");
    }
    public void showWriteForm(Rq rq) {
        rq.setAttr("articles", articleService.getArticles());
        rq.view("usr/article/write");
    }
    public void doWrite(Rq rq) {
        String title = rq.getParam("title", "");
        String content = rq.getParam("content", "");

        if (title.isEmpty()) {
            rq.setAttr("errorMessage", "제목을 입력해주세요.");
            rq.view("usr/article/write");
            return;
        }

        if (content.isEmpty()) {
            rq.setAttr("errorMessage", "내용을 입력해주세요.");
            rq.view("usr/article/write");
            return;
        }

        articleService.writeArticle(title, content);
        rq.setAttr("successMessage", "게시글이 등록되었습니다.");
        rq.redirect("usr/article/list");
    }
}
