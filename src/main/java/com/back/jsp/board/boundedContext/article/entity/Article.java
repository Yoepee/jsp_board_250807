package com.back.jsp.board.boundedContext.article.entity;

import com.back.jsp.board.boundedContext.article.dto.ArticleDto;
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
    private long authorId;

    public Article(String title, String content, long author) {
        this.title = title;
        this.content = content;
        this.authorId = author;
    }
    public Article(Map<String, Object> row){
        this.id = Long.parseLong(row.get("id").toString());
        this.title = (String) row.get("title");
        this.content = (String) row.get("content");
        this.count = Long.parseLong(row.get("count").toString());
        this.regDate = LocalDateTime.parse(row.get("regDate").toString()).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.authorId = Long.parseLong(row.get("author_id").toString());
    }
    public Article(ArticleDto articleDto){
        this.id = articleDto.getId();
        this.title = articleDto.getTitle();
        this.content = articleDto.getContent();
        this.count = articleDto.getCount();
        this.regDate = articleDto.getRegDate();
        this.authorId = articleDto.getAuthorId();
    }

    public boolean isNew() {
        return id == 0;
    }
}
