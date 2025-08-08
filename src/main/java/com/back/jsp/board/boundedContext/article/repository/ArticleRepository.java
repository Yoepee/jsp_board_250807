package com.back.jsp.board.boundedContext.article.repository;

import com.back.jsp.board.boundedContext.article.entity.Article;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.LongStream;

@Getter
public class ArticleRepository {
    private List<Article> articles = new ArrayList<>();

    public ArticleRepository() {
        LongStream.rangeClosed(1, 5).forEach(
                i -> articles.add(
                        new Article((int) i, "제목 " + i, "내용 " + i)
                )
        );
    }
}
