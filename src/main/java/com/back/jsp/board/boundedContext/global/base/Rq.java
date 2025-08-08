package com.back.jsp.board.boundedContext.global.base;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

public class Rq {
    private final HttpServletRequest req;
    private final HttpServletResponse resp;
    private Map<String, Object> paramMap;

    public Rq(HttpServletRequest req, HttpServletResponse resp){
        this.req = req;
        this.resp = resp;

        try {
            req.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("UTF-8 인코딩 설정에 실패했습니다.", e);
        }

        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
    }

    public String getParam(String paramName, String defaultValue) {
        try {
            String value = req.getParameter(paramName);
            return value != null ? value : defaultValue;
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public int getParamAsInt(String paramName, int defaultValue) {
        try {
            String value = getParam(paramName, String.valueOf(defaultValue));
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public void appendBody(String s) {
        try {
            resp.getWriter().append(s);
        } catch (IOException e) {
            throw new RuntimeException("응답 작성 중 오류가 발생했습니다.", e);
        }
    }

    public void view(String path) {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/jsp/%s.jsp".formatted(path));
        try {
            requestDispatcher.forward(req, resp);
        } catch (ServletException e) {
            throw new RuntimeException("뷰로 포워딩 중 오류가 발생했습니다.", e);
        } catch (IOException e) {
            throw new RuntimeException("뷰로 포워딩 중 IO 오류가 발생했습니다.", e);
        }
    }

    public void setAttr(String key, Object value) {
        req.setAttribute(key, value);
    }

    public void redirect(String path) {
        try {
            resp.sendRedirect("/%s".formatted(path));
        } catch (IOException e) {
            throw new RuntimeException("리다이렉트 중 오류가 발생했습니다.", e);
        }
    }

    public String getUrlPath() {
        return req.getRequestURI();
    }
}
