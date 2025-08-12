package com.back.jsp.board.boundedContext.global;

import com.back.jsp.board.boundedContext.article.controller.ArticleController;
import com.back.jsp.board.boundedContext.base.Container;
import com.back.jsp.board.boundedContext.global.base.Rq;
import com.back.jsp.board.boundedContext.home.controller.HomeController;
import com.back.jsp.board.boundedContext.member.controller.MemberController;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

;

@WebServlet("/usr/*")
public class DispatchServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Rq rq = new Rq(req, resp);

        ArticleController articleController = Container.articleController;
        MemberController memberController =  Container.memberController;
        HomeController homeController = Container.homeController;

        switch(rq.getActionPath()) {
            case "/usr/home" -> homeController.showHome(rq);
            case "/usr/article/list" -> articleController.showList(rq);
            case "/usr/article/write" -> articleController.showWriteForm(rq);
            case "/usr/article/detail" -> articleController.showDetail(rq);
            case "/usr/article/modify" -> articleController.showModify(rq);
            case "/usr/member/join" -> memberController.showJoin(rq);
            case "/usr/member/login" -> memberController.showLogin(rq);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Rq rq = new Rq(req, resp);

        ArticleController articleController = Container.articleController;
        MemberController memberController =  Container.memberController;

        switch(rq.getActionPath()) {
            case "/usr/article/write" -> articleController.doWrite(rq);
            case "/usr/article/modify" -> articleController.doModify(rq);
            case "/usr/article/delete" -> articleController.doDelete(rq);
            case "/usr/member/join" -> memberController.doJoin(rq);
            case "/usr/member/login" -> memberController.doLogin(rq);
        }
    }
}
