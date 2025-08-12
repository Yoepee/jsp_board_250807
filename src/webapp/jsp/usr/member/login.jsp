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

<h1> 로그인 </h1>
<div>
    <form onsubmit="handleMemberSubmit(this)" method="post">
        <div>
            <label for="username">아이디:</label>
            <input class="border" type="text" id="username" name="username" required>
        </div>
        <div>
            <label for="password">비밀번호:</label>
            <input class="border" type="password" id="password" name="password" required></input>
        </div>
        <div>
            <button type="submit">로그인</button>
            <button type="button" onclick="location.href='/'">취소</button>
        </div>
    </form>
</div>
<%@ include file="/jsp/common/foot.jspf" %>