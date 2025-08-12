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
    public Member findByUserName(String username) {
        Member member = memberRepository.findByUserName(username);
        if (member == null) {
            return null; // 회원이 존재하지 않음
        }
        return member; // 회원 정보 반환
    }

    public Member loginMember(String username, String password) {
        Member member = findByUserName(username);
        if (member == null || !member.getPassword().equals(password)) {
            return null; // 로그인 실패
        }
        return member; // 로그인 성공
    }
}
