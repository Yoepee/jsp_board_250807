package com.back.jsp.board.boundedContext.member.controller;

import com.back.jsp.board.boundedContext.global.base.Rq;

;

public class MemberController {
    public void showJoin(Rq rq) {
        rq.appendBody("회원가입 페이지입니다.");
    }
}
