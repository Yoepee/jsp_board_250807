package com.back.jsp.board.boundedContext.article.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public class Article {
    private long id;
    private String title;
    private String content;
    private long count;
    private String regDate;
    private String author;

    public Article(String title, String content) {
        this.title = title;
        this.content = content;
        this.regDate = null;
        this.author = null;
    }
    public Article(Map<String, Object> row){
        this.id = Long.parseLong(row.get("id").toString());
        this.title = (String) row.get("title");
        this.content = (String) row.get("content");
        this.count = Long.parseLong(row.get("count").toString());
        this.regDate = LocalDateTime.parse(row.get("regDate").toString()).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.author = (String) row.get("author");
    }

    public boolean isNew() {
        return id == 0;
    }

    public void increaseCount() {
        this.count++;
    }
}
