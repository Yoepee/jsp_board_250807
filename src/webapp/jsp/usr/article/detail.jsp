<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/head.jspf" %>

<button type="button" onclick="location.href = '${pageContext.request.contextPath}/usr/article/list'">목록보기</button>
<h1> 게시물 상세보기 </h1>
<div>
        <div>
            <p>${article.title}</p>
            <p>${article.content}</p>
            <p>조회수: ${article.count}</p>
        </div>
</div>

<%@ include file="/jsp/common/foot.jspf" %>