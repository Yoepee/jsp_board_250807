package com.back.jsp.board.boundedContext.member.repository;

import com.back.jsp.board.boundedContext.base.Container;
import com.back.jsp.board.boundedContext.member.entity.Member;
import com.back.jsp.board.db.DBConnection;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MemberRepository {
    List<Member> members;
    private DBConnection dbConnection;

    public MemberRepository() {
        members = new ArrayList<>();
        dbConnection = Container.dbConnection;
    }
    private List<Member> LoadDBRows() {
        List<Map<String, Object>> rows = dbConnection.selectRows("SELECT * FROM articles");
        for (Map<String, Object> row : rows) {
            Member article = new Member(row);
            members.add(article);
        }

        return members;
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
        Map<String, Object> row = dbConnection.selectRow("""
                SELECT * 
                FROM members 
                WHERE username = %s;
                """.formatted(username));
        if (row == null) {
            return null;
        }
        return new Member(row);
    }
}
