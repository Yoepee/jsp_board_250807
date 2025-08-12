<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/head.jspf" %>

<script>
    function handleDeleteArticle() {
        if (!confirm("정말 삭제하시겠습니까?")) {
            event.preventDefault();
            return false;
        }
    }
</script>

<div>
    <form action="${pageContext.request.contextPath}/usr/article/delete/${article.id}" method="post">
        <button type="button" onclick="location.href = '${pageContext.request.contextPath}/usr/article/list'">목록보기</button>
        <button type="button" onclick="location.href = '${pageContext.request.contextPath}/usr/article/modify/${article.id}'">수정하기</button>
        <button type="submit" onclick="handleDeleteArticle()">삭제하기</button>
    </form>
</div>
<h1> 게시물 상세보기 </h1>
<div>
        <div>
            <p>${article.title}</p>
            <p>${article.content}</p>
            <p>조회수: ${article.count}</p>
        </div>
</div>

<%@ include file="/jsp/common/foot.jspf" %>