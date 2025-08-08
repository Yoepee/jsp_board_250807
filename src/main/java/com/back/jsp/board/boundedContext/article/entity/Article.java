package com.back.jsp.board.boundedContext.article.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Article {
    private int id;
    private String title;
    private String content;
    private int count;

    public Article(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public boolean isNew() {
        return id == 0;
    }

    public void increaseCount() {
        this.count++;
    }
}
