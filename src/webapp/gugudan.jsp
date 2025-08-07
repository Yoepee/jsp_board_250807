<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.back.jsp.board.Rq"%>

<%
Rq rq = new Rq(request, response);
int dan = rq.getParamAsInt("dan", 9);
int limit = rq.getParamAsInt("limit", 9);
%>

<h1><%= dan%> 단</h1>
<%for(int i = 1; i <= limit; i++) {%>
    <p><%= dan%> x <%= i%> = <%= dan * i%></p>
<%}%>