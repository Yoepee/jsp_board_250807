package com.back.jsp.board.boundedContext.article.controller;

import com.back.jsp.board.boundedContext.article.entity.Article;
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
    public void showDetail(Rq rq) {
        long id = rq.getLongPathValueIndex(1, -1);
        if (id == -1) {
            rq.replace("유효하지 않은 아이디입니다.".formatted(id), "/usr/article/list");
            return;
        }

        Article article = articleService.getDetailById(id);
        if (article == null) {
            rq.replace("%d번 게시글이 존재하지 않습니다.".formatted(id), "/usr/article/list");
            return;
        }

        rq.setAttr("article", article);
        rq.view("usr/article/detail");
    }
    public void showModify(Rq rq) {
        long id = rq.getLongPathValueIndex(1, -1);
        if (id == -1) {
            rq.replace("유효하지 않은 아이디입니다.".formatted(id), "/usr/article/list");
            return;
        }

        Article article = articleService.getDetailById(id);
        if (article == null) {
            rq.replace("%d번 게시글이 존재하지 않습니다.".formatted(id), "/usr/article/list");
            return;
        }

        // TODO: 데이터 불러오기
        rq.setAttr("article", article);
        rq.view("usr/article/modify");
    }
    public void doWrite(Rq rq) {
        String title = rq.getParam("title", "").trim();
        String content = rq.getParam("content", "").trim();
        long authorId = Long.parseLong(String.valueOf(rq.getParam("author", "-1")));

        if (authorId == -1) {
            rq.historyBack("유효하지 않은 접근입니다.");
            return;
        }

        if (title.isEmpty()) {
            rq.historyBack("제목을 입력해주세요.");
            return;
        }

        if (content.isEmpty()) {
            rq.historyBack("내용을 입력해주세요.");
            return;
        }

        Article article = articleService.writeArticle(title, content, authorId);
        rq.replace("%d번 게시글이 등록되었습니다.".formatted(article.getId()), "/usr/article/detail/%d".formatted(article.getId()));
    }

    public void doModify(Rq rq) {
        Long id = rq.getLongPathValueIndex(1, -1);
        if (id == -1) {
            rq.replace("유효하지 않은 아이디입니다.".formatted(id), "/usr/article/list");
            return;
        }

        String title = rq.getParam("title", "").trim();
        String content = rq.getParam("content", "").trim();

        if (title.isEmpty()) {
            rq.historyBack("제목을 입력해주세요.");
            return;
        }

        if (content.isEmpty()) {
            rq.historyBack("내용을 입력해주세요.");
            return;
        }

        Article article = articleService.getDetailById(id);
        if (article == null) {
            rq.replace("%d번 게시글이 존재하지 않습니다.".formatted(id), "/usr/article/list");
            return;
        }

        articleService.modifyArticle(article, title, content);
        rq.replace("%d번 게시글이 수정되었습니다.".formatted(article.getId()), "/usr/article/detail/%d".formatted(article.getId()));
    }
    public void doDelete(Rq rq) {
        Long id = rq.getLongPathValueIndex(1, -1);
        if (id == -1) {
            rq.replace("유효하지 않은 아이디입니다.".formatted(id), "/usr/article/list");
            return;
        }

        boolean doDelete = articleService.deleteArticle(id);
        if (!doDelete) {
            rq.replace("%d번 게시글이 존재하지 않습니다.".formatted(id), "/usr/article/list");
            return;
        }

        rq.replace("%d번 게시글이 삭제되었습니다.".formatted(id), "/usr/article/list");
    }
}
