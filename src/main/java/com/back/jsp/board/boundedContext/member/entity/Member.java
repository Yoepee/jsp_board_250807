package com.back.jsp.board.boundedContext.member.entity;

import lombok.Data;

import java.util.Map;

@Data
public class Member {
    private long id = 0;
    private String username;
    private String password;
    private String name;

    public Member(Map<String, Object> row){
        this.id = Long.parseLong(row.get("id").toString());
        this.username = (String) row.get("username");
        this.password = (String) row.get("password");
        this.name = (String) row.get("name");
    }

    public Member(String username, String password, String name) {
        this.username = username;
        this.password = password;
        this.name = name;
    }

    public boolean isNew() {
        return id == 0;
    }
}
