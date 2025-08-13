package com.back.jsp.board.boundedContext.article.controller;

import com.back.jsp.board.boundedContext.article.dto.ArticleDto;
import com.back.jsp.board.boundedContext.article.entity.Article;
import com.back.jsp.board.boundedContext.article.service.ArticleService;
import com.back.jsp.board.boundedContext.base.Container;
import com.back.jsp.board.boundedContext.global.base.Rq;
import com.back.jsp.board.boundedContext.member.entity.Member;

public class ArticleController {
    private ArticleService articleService;

    public ArticleController() {
        this.articleService = Container.articleService;
    }
    public void showList(Rq rq) {
        rq.setAttr("articleDtos", articleService.findJoinedMemberAll());
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

        ArticleDto articleDto = articleService.getJoinedMemberById(id);
        if (articleDto == null) {
            rq.replace("%d번 게시글이 존재하지 않습니다.".formatted(id), "/usr/article/list");
            return;
        }
        articleDto = articleService.increaseCount(articleDto);

        rq.setAttr("articleDto", articleDto);
        rq.view("usr/article/detail");
    }
    public void showModify(Rq rq) {
        long id = rq.getLongPathValueIndex(1, -1);
        if (id == -1) {
            rq.replace("유효하지 않은 아이디입니다.".formatted(id), "/usr/article/list");
            return;
        }

        Article article = articleService.getArticleById(id);
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

        if (title.isEmpty()) {
            rq.historyBack("제목을 입력해주세요.");
            return;
        }

        if (content.isEmpty()) {
            rq.historyBack("내용을 입력해주세요.");
            return;
        }

        Member loginedMember = rq.getLoginedMember();
        if (loginedMember == null) {
            rq.historyBack("로그인이 필요합니다.");
            return;
        }
        Article article = articleService.writeArticle(title, content, loginedMember);
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

        Article article = articleService.getArticleById(id);
        if (article == null) {
            rq.replace("%d번 게시글이 존재하지 않습니다.".formatted(id), "/usr/article/list");
            return;
        }
        Member loginedMember = rq.getLoginedMember();
        if (loginedMember == null) {
            rq.historyBack("로그인이 필요합니다.");
            return;
        }
        if (article.getAuthorId() != loginedMember.getId()) {
            rq.historyBack("본인이 작성한 게시글만 수정할 수 있습니다.");
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

        Article article = articleService.getArticleById(id);
        if (article == null) {
            rq.replace("%d번 게시글이 존재하지 않습니다.".formatted(id), "/usr/article/list");
            return;
        }

        Member loginedMember = rq.getLoginedMember();
        if (loginedMember == null) {
            rq.historyBack("로그인이 필요합니다.");
            return;
        }
        if (article.getAuthorId() != loginedMember.getId()) {
            rq.historyBack("본인이 작성한 게시글만 수정할 수 있습니다.");
            return;
        }

        articleService.deleteArticle(article);
        rq.replace("%d번 게시글이 삭제되었습니다.".formatted(id), "/usr/article/list");
    }
}
