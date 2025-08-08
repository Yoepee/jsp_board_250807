<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.back.jsp.board.boundedContext.article.entity.Article"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>

    <h1> 게시물 리스트 </h1>

        <div>
            <ul>
                <%
                List<Article> articles = (List<Article>)request.getAttribute("articles");
                for (Article article : articles) {
                pageContext.setAttribute("article", article);
                %>
                <li>${article.id}번 : ${article.title}</li>
                <% } %>
            </ul>
        </div>