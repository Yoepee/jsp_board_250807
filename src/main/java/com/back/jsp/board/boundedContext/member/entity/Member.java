package com.back.jsp.board.boundedContext.member.entity;

import lombok.Data;

@Data
public class Member {
    private int id = 0;
    private String username;
    private String password;
    private String name;

    public Member(String username, String password, String name) {
        this.username = username;
        this.password = password;
        this.name = name;
    }

    public boolean isNew() {
        return id == 0;
    }
}
