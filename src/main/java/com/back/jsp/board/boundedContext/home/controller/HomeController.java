package com.back.jsp.board.boundedContext.home.controller;

import com.back.jsp.board.boundedContext.global.base.Rq;

public class HomeController {
    public void showHome(Rq rq) {
        rq.view("../index");
    }
}
