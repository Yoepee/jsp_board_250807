<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/head.jspf" %>

<div class="container px-10 my-5">
    <div class="flex items-center justify-between mb-6">
        <h1 class="text-2xl font-bold text-gray-800">게시물 리스트</h1>
        <button type="button"
                class="px-4 py-2 bg-blue-500 text-white rounded hover:bg-blue-600 transition"
                onclick="location.href='${pageContext.request.contextPath}/usr/article/write'">
            작성하기
        </button>
    </div>
    <div>
        <table class="min-w-full border border-gray-300 rounded-lg overflow-hidden shadow-sm">
            <thead class="bg-gray-100">
            <tr>
                <th class="px-4 py-2 text-left text-sm font-semibold text-gray-600">번호</th>
                <th class="px-4 py-2 text-left text-sm font-semibold text-gray-600">내용</th>
                <th class="px-4 py-2 text-left text-sm font-semibold text-gray-600">작성자</th>
                <th class="px-4 py-2 text-left text-sm font-semibold text-gray-600">작성일</th>
                <th class="px-4 py-2 text-left text-sm font-semibold text-gray-600">조회수</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="article" items="${articles}" varStatus="status">
                <tr class="hover:bg-gray-50 cursor-pointer transition hover:bg-gray-100"
                    onclick="location.href = '${pageContext.request.contextPath}/usr/article/detail/${article.id}'">
                    <td class="px-4 py-2 border-t">${article.id}</td>
                    <td class="px-4 py-2 border-t font-medium">${article.title}</td>
                    <td class="px-4 py-2 border-t text-center">${article.author != null ? article.author : '-'}</td>
                    <td class="px-4 py-2 border-t text-gray-500">${article.regDate}</td>
                    <td class="px-4 py-2 border-t text-center">${article.count}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<%@ include file="/jsp/common/foot.jspf" %>