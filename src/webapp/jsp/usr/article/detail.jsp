<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/head.jspf" %>
<div class="flex gap-2 mb-6">
    <button type="button"
            class="px-4 py-2 bg-blue-500 text-white rounded hover:bg-blue-600 transition"
            onclick="location.href='${pageContext.request.contextPath}/usr/article/modify/${articleDto.id}'">
        수정하기
    </button>
    <button type="button"
            id="deleteBtn"
            class="px-4 py-2 bg-red-500 text-white rounded hover:bg-red-600 transition">
        삭제하기
    </button>
    <form hidden id="deleteForm" action="${pageContext.request.contextPath}/usr/article/delete/${articleDto.id}"
          method="post">
        <input type="hidden" name="id" value="${article.id}">
    </form>
</div>

<!-- 제목 -->
<h1 class="text-2xl font-bold text-gray-800 mb-4">${article.title}</h1>

<!-- 상세 내용 카드 -->
<div class="border border-gray-200 rounded-lg p-4 bg-white shadow-sm space-y-2">
    <p>
        <span class="font-semibold">내용:</span>
        <span class="whitespace-pre-wrap">${articleDto.content}</span>
    </p>
    <p>
        <span class="font-semibold">작성자:</span>
        ${articleDto.authorName}
    </p>
    <p class="text-gray-600">
        <span class="font-semibold">작성일:</span>
        ${articleDto.regDate}
    </p>
    <p class="text-gray-600">
        <span class="font-semibold">조회수:</span>
        ${articleDto.count}
    </p>
</div>

<script>
    document.getElementById("deleteBtn").addEventListener("click", function () {
        if (confirm("정말 삭제하시겠습니까?")) {
            document.getElementById("deleteForm").submit();
        }
    });
</script>

<%@ include file="/jsp/common/foot.jspf" %>