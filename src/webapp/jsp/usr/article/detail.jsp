<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/head.jspf" %>

<div class="flex flex-col">
    <button type="button" onclick="location.href = '${pageContext.request.contextPath}/usr/article/list'">목록보기</button>
    <button type="button" onclick="location.href = '${pageContext.request.contextPath}/usr/article/modify/${article.id}'">수정하기</button>
    <button type="button" id="deleteBtn">삭제하기</button>
    <form hidden id="deleteForm" action="${pageContext.request.contextPath}/usr/article/delete/${article.id}" method="post">
        <input type="hidden" name="id" value="${article.id}">
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

<script>
    document.getElementById("deleteBtn").addEventListener("click", function() {
        if (confirm("정말 삭제하시겠습니까?")) {
            document.getElementById("deleteForm").submit();
        }
    });
</script>

<%@ include file="/jsp/common/foot.jspf" %>