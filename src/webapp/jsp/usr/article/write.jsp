<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/head.jspf" %>

<script>
    function handleArticleSubmit(form) {
        const title = form.title.value.trim();
        const content = form.content.value.trim();

        if (title.length == 0) {
            event.preventDefault();
            alert("제목을 입력해주세요.");
            form.title.value = title;
            form.title.focus();
            return false;
        } else if (content.length == 0) {
            event.preventDefault();
            alert("내용을 입력해주세요.");
            form.content.value = content;
            form.content.focus();
            return false;
        }
    }
</script>

<h1 class="text-2xl font-bold text-gray-800 mb-6">게시물 작성</h1>

<div class="max-w-2xl bg-white border border-gray-200 rounded-lg shadow-sm p-6">
    <form onsubmit="handleArticleSubmit(this)" method="post" class="space-y-4">

        <!-- 제목 -->
        <div>
            <label for="title" class="block text-sm font-medium text-gray-700 mb-1">제목</label>
            <input type="text" id="title" name="title" required
                   class="w-full border border-gray-300 rounded px-3 py-2 focus:outline-none focus:ring-2 focus:ring-blue-400">
        </div>

        <!-- 내용 -->
        <div>
            <label for="content" class="block text-sm font-medium text-gray-700 mb-1">내용</label>
            <textarea id="content" name="content" rows="6" required
                      class="w-full border border-gray-300 rounded px-3 py-2 focus:outline-none focus:ring-2 focus:ring-blue-400"></textarea>
        </div>

        <!-- 버튼 -->
        <div class="flex gap-2 pt-2">
            <button type="submit"
                    class="flex-1 bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-600 transition">
                작성하기
            </button>
            <button type="button" onclick="history.back()"
                    class="flex-1 bg-gray-300 text-gray-800 px-4 py-2 rounded hover:bg-gray-400 transition">
                취소
            </button>
        </div>

    </form>
</div>

<%@ include file="/jsp/common/foot.jspf" %>