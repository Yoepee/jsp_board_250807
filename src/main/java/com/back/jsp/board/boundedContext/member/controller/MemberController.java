package com.back.jsp.board.boundedContext.member.controller;

import com.back.jsp.board.Rq;

public class MemberController {
    public void showJoin(Rq rq) {
        rq.writer("회원가입 페이지입니다.");
    }
}
