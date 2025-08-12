package com.back.jsp.board.boundedContext.member.service;

import com.back.jsp.board.boundedContext.base.Container;
import com.back.jsp.board.boundedContext.member.entity.Member;
import com.back.jsp.board.boundedContext.member.repository.MemberRepository;

public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService() {
        memberRepository = Container.memberRepository;
    }

    public Member joinMember(String username, String password, String name) {
        Member member = new Member(username, password, name);
        return memberRepository.saveMember(member);
    }
}
