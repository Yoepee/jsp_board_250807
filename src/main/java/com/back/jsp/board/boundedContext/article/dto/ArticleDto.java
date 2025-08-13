package com.back.jsp.board.boundedContext.article.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public class ArticleDto {
    private long id;
    private String title;
    private String content;
    private long count;
    private String regDate;
    private String authorName;
    private long authorId;

    public ArticleDto(Map<String, Object> row){
        this.id = Long.parseLong(row.get("id").toString());
        this.title = (String) row.get("title");
        this.content = (String) row.get("content");
        this.count = Long.parseLong(row.get("count").toString());
        this.regDate = LocalDateTime.parse(row.get("regDate").toString()).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.authorName = (String) row.get("author_name");
        this.authorId = Long.parseLong(row.get("author_id").toString());
    }

    public void increaseCount() {
        this.count++;
    }
}
