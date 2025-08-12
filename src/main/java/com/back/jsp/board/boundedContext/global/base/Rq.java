package com.back.jsp.board.boundedContext.global.base;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Map;

public class Rq {
    private final HttpServletRequest req;
    private final HttpServletResponse resp;
    private final HttpSession session;
    private Map<String, Object> paramMap;

    public Rq(HttpServletRequest req, HttpServletResponse resp){
        this.req = req;
        this.resp = resp;
        this.session = req.getSession();

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
    public void setSessionAttr(String key, Object value) {
        session.setAttribute(key, value);
    }

    public void removeSessionAttr(String key) {
        session.removeAttribute(key);
    }

    public boolean hasSessionAttr(String key) {
        return session.getAttribute(key) != null;
    }
    public boolean isLogined() {
        return hasSessionAttr("loginedMember");
    }
    public boolean isLogout() {
        return !hasSessionAttr("loginedMember");
    }

    public void redirect(String path) {
        try {
            String ctx = req.getContextPath();
            String target = path.startsWith("/") ? ctx + path : ctx + "/" + path;
            resp.sendRedirect(target); // 302
        } catch (IOException e) {
            throw new RuntimeException("리다이렉트 중 오류가 발생했습니다.", e);
        }
    }

    public void printMessage(String message) {
        appendBody("""
                <script>
                alert(`%s`);
                </script>
                """.formatted(message));
    }
    public void historyBack(String message) {
        printMessage(message);
        appendBody("""
                <script>
                history.back();
                </script>
                """);
    }

    public void replace(String message, String url){
        printMessage(message);
        appendBody("""
                <script>
                location.replace('%s');
                </script>
                """.formatted(url));
    }

    public String getActionPath() {
        String[] bits = req.getRequestURI().trim().split("/");
        bits = Arrays.stream(bits).filter(bit -> !bit.isEmpty()).toArray(String[]::new);
        if (bits.length < 3) {
            return "/usr/home";
        }

        return "/%s/%s/%s".formatted(bits[0], bits[1], bits[2]);
    }

    public String getStringPathValueIndex(int index, String defaultValue) {
        String[] bits = req.getRequestURI().trim().split("/");
        bits = Arrays.stream(bits).filter(bit -> !bit.isEmpty()).skip(3).toArray(String[]::new);

        if (index <= 0 || index > bits.length) {
            return defaultValue;
        }

        return bits[index-1];
    }

    public long getLongPathValueIndex(int index, long defaultValue) {
        try {
            return Long.parseLong(getStringPathValueIndex(index, String.valueOf(defaultValue)));
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
}
