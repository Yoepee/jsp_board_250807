package com.back.jsp.board.boundedContext.member.controller;

import com.back.jsp.board.boundedContext.base.Container;
import com.back.jsp.board.boundedContext.global.base.Rq;
import com.back.jsp.board.boundedContext.member.entity.Member;
import com.back.jsp.board.boundedContext.member.service.MemberService;

;

public class MemberController {
    private final MemberService memberService;

public MemberController() {
        this.memberService = Container.memberService;
    }
    public void showJoin(Rq rq) {
        rq.view("usr/member/join");
    }
    public void showLogin(Rq rq) {
        rq.view("usr/member/login");
    }

    public void doJoin(Rq rq) {
        String username = rq.getParam("username", "").trim();
        String password = rq.getParam("password", "").trim();
        String name = rq.getParam("name", "").trim();

        if (username.isEmpty()) {
            rq.historyBack("아이디를 입력해주세요.");
            return;
        }else if (password.isEmpty()) {
            rq.historyBack("비밀번호를 입력해주세요.");
            return;
        }else if (name.isEmpty()) {
            rq.historyBack("이름을 입력해주세요.");
            return;
        }

        Member hasMember = memberService.findByUserName(username);
        if (hasMember != null) {
            rq.replace("이미 존재하는 아이디입니다.", "/usr/member/join");
            return;
        }

        Member member = memberService.joinMember(username, password, name);
        rq.setSessionAttr("loginedMember", member);
        rq.replace("""
                회원가입이 성공하었습니다.
                %s님 환영합니다.
                """.formatted(member.getName()), "/");
    }

    public void doLogin(Rq rq) {
        String username = rq.getParam("username", "").trim();
        String password = rq.getParam("password", "").trim();

        if (username.isEmpty()) {
            rq.historyBack("아이디를 입력해주세요.");
            return;
        } else if (password.isEmpty()) {
            rq.historyBack("비밀번호를 입력해주세요.");
            return;
        }

        Member member = memberService.loginMember(username, password);
        if (member == null) {
            rq.historyBack("아이디 또는 비밀번호가 일치하지 않습니다.");
            return;
        }

        rq.setSessionAttr("loginedMember", member);
        rq.replace("%s님 환영합니다.".formatted(member.getName()), "/");
    }

    public void doLogout(Rq rq) {
        rq.removeSessionAttr("loginMemberId");
        rq.replace("로그아웃 되었습니다.", "/");
    }
}
