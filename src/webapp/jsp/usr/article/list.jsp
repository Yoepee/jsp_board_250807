<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h1> 게시물 리스트 </h1>
<div >
    <button type="button" onclick="location.href = '${pageContext.request.contextPath}/usr/article/write'">작성하기</button>
</div>
<div>
    <table border="1">
        <thead>
        <tr>
            <th>번호</th>
            <th>내용</th>
        </tr>
        </thead>
        <tbody>
            <c:forEach var="article" items="${articles}" varStatus="status">
                <tr onclick="location.href = '${pageContext.request.contextPath}/usr/article/detail?id=${article.id}'">
                    <td>${article.id}</td>
                    <td>${article.title}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>