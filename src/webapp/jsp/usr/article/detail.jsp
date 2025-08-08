<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h1> 게시물 상세보기 </h1>
<div>
    <c:forEach var="articles" items="${article}">
        <p>${article.id}</p>
        <p>${article.title}</p>
        <p>${article.content}</p>
    </c:forEach>
</div>