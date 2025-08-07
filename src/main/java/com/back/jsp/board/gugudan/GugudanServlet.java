package com.back.jsp.board.gugudan;

import com.back.jsp.board.Rq;
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
        Rq rq = new Rq(req, resp);

        int dan = rq.getParamAsInt("dan", -1);
        int limit = rq.getParamAsInt("limit", 10);


        if (dan == -1){
            range(2,10).forEach(
                    i -> {
                        rq.writer(printDan(i, limit));
                    }
            );
        } else {
            rq.writer(printDan(dan, limit));
        }
    }

    public String printDan(int dan, int limit) {
        StringBuilder sb = new StringBuilder();
        sb.append("<h2>%d단</h2>".formatted(dan));
        range(1, limit + 1).forEach(j -> {
            sb.append("%d x %d = %d <br>".formatted(dan, j, dan * j));
        });
        return sb.toString();
    }
}
