package com.back.jsp.board.boundedContext.article.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public class Article {
    private long id;
    private String title;
    private String content;
    private long count;

    public Article(String title, String content) {
        this.title = title;
        this.content = content;
    }
    public Article(Map<String, Object> row){
        this.id = Long.parseLong(row.get("id").toString());
        this.title = (String) row.get("title");
        this.content = (String) row.get("content");
        this.count = Long.parseLong(row.get("count").toString());
    }

    public boolean isNew() {
        return id == 0;
    }

    public void increaseCount() {
        this.count++;
    }
}
