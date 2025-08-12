package com.back.jsp.board.boundedContext.member.repository;

import com.back.jsp.board.boundedContext.member.entity.Member;

import java.util.ArrayList;
import java.util.List;

public class MemberRepository {
    List<Member> members;
    private int lastId;

    public MemberRepository() {
        members = new ArrayList<>();
        lastId = 0;
    }

    public Member saveMember(Member member) {
        if (member.isNew()) {
            member.setId(++lastId);
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
