package com.back.jsp.board.boundedContext.global;

import com.back.jsp.board.Rq;
import com.back.jsp.board.boundedContext.article.controller.ArticleController;
import com.back.jsp.board.boundedContext.base.Container;
import com.back.jsp.board.boundedContext.member.controller.MemberController;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/usr/*")
public class DispatchServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Rq rq = new Rq(req, resp);

        ArticleController articleController = Container.articleController;
        MemberController memberController =  Container.memberController;

        String url = req.getRequestURI();

        switch(url) {
            case "/usr/article/list" -> articleController.showList(rq);
            case "/usr/memeber/join" -> memberController.showJoin(rq);
        }
    }
}
