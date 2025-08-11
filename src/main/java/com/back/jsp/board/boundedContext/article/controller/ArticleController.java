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

        Article article = articleService.getDetailById(id);
        if (article == null) {
            rq.appendBody("""
                    <script>
                        alert('해당 게시글이 존재하지 않습니다.');
                        history.back();
                    </script>
                    """);
            return;
        }

        rq.setAttr("article", article);
        rq.view("usr/article/detail");
    }
    public void doWrite(Rq rq) {
        String title = rq.getParam("title", "").trim();
        String content = rq.getParam("content", "").trim();

        if (title.isEmpty()) {
            rq.appendBody("""
                    <script>
                        alert('제목을 입력해주세요.');
                        history.back();
                    </script>
                    """);
            return;
        }

        if (content.isEmpty()) {
            rq.appendBody("""
                    <script>
                        alert('내용을 입력해주세요.');
                        history.back();
                    </script>
                    """);
            return;
        }

        articleService.writeArticle(title, content);
        rq.setAttr("successMessage", "게시글이 등록되었습니다.");
        rq.redirect("usr/article/list");
    }
}
