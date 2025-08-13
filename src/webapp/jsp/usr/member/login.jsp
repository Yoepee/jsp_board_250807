<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/head.jspf" %>
<script>
    function handleMemberSubmit(form) {
        const username = form.username.value.trim();
        const password = form.password.value.trim()

        if (username.length == 0) {
            event.preventDefault();
            alert("제목을 입력해주세요.");
            form.username.value = title;
            form.username.focus();
            return false;
        } else if (password.length == 0) {
            event.preventDefault();
            alert("내용을 입력해주세요.");
            form.password.value = content;
            form.password.focus();
            return false;
        }
    }
</script>

<h1 class="text-2xl font-bold text-gray-800 mb-6">로그인</h1>

<div class="max-w-md bg-white border border-gray-200 rounded-lg shadow-sm p-6">
    <form onsubmit="handleMemberSubmit(this)" method="post" class="space-y-4">
        <!-- 아이디 -->
        <div>
            <label for="username" class="block text-sm font-medium text-gray-700 mb-1">아이디</label>
            <input type="text" id="username" name="username" required
                   class="w-full border border-gray-300 rounded px-3 py-2 focus:outline-none focus:ring-2 focus:ring-blue-400">
        </div>

        <!-- 비밀번호 -->
        <div>
            <label for="password" class="block text-sm font-medium text-gray-700 mb-1">비밀번호</label>
            <input type="password" id="password" name="password" required
                   class="w-full border border-gray-300 rounded px-3 py-2 focus:outline-none focus:ring-2 focus:ring-blue-400">
        </div>

        <!-- 버튼 -->
        <div class="flex gap-2 pt-2">
            <button type="submit"
                    class="flex-1 bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-600 transition">
                로그인
            </button>
            <button type="button"
                    onclick="location.href='/'"
                    class="flex-1 bg-gray-300 text-gray-800 px-4 py-2 rounded hover:bg-gray-400 transition">
                취소
            </button>
        </div>
    </form>
</div>
<%@ include file="/jsp/common/foot.jspf" %>