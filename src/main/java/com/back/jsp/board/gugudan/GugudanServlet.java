package com.back.jsp.board.gugudan;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static java.util.stream.IntStream.range;

@WebServlet("/gugudan")
public class GugudanServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        String dan = req.getParameter("dan");
        System.out.println("dan = " + dan);

        if (dan != null && !dan.isEmpty()) {
            int danNum = Integer.parseInt(dan);
            printDan(danNum, resp);
        } else {
            range(2,10).forEach(
                    i -> {
                        try {
                            printDan(i, resp);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
            );
        }
    }

    public void printDan(int dan, HttpServletResponse resp) throws IOException {
        resp.getWriter().append("<h2>%d단</h2>".formatted(dan));
        range(1, 10).forEach(j -> {
            try {
                resp.getWriter().append("%d x %d = %d <br>".formatted(dan, j, dan*j));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
