package com.back.jsp.board.boundedContext.member.repository;

import com.back.jsp.board.boundedContext.base.Container;
import com.back.jsp.board.boundedContext.member.entity.Member;
import com.back.jsp.board.db.DBConnection;

import java.util.ArrayList;
import java.util.List;

public class MemberRepository {
    List<Member> members;
    private DBConnection dbConnection;

    public MemberRepository() {
        members = new ArrayList<>();
        dbConnection = Container.dbConnection;
    }

    public Member saveMember(Member member) {
        if (member.isNew()) {
            int id = dbConnection.insert("INSERT INTO members (username, password, name) VALUES ('%s', '%s', '%s')".formatted(member.getUsername(), member.getPassword(), member.getName()));
            member.setId(id);
            members.add(member);
        }

        return member;
    }

    public Member findByUserName(String username) {
        return members.stream()
                .filter(member -> member.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }
}
