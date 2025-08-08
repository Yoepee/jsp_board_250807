package com.back.jsp.board.boundedContext.article.controller;

import com.back.jsp.board.Rq;

public class ArticleController {
    public void showList(Rq rq) {
        rq.writer("게시글 목록입니다.");
    }
}
