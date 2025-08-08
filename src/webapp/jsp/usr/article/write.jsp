<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>
    function handleArticleSubmit(form) {
        const title = form.title.value.trim();
        const content = form.content.value.trim();

        // if (title.length == 0) {
        //     event.preventDefault();
        //     alert("제목을 입력해주세요.");
        //     form.title.value = title;
        //     form.title.focus();
        //     return false;
        // } else if (content.length == 0) {
        //     event.preventDefault();
        //     alert("내용을 입력해주세요.");
        //     form.content.value = content;
        //     form.content.focus();
        //     return false;
        // }
    }
</script>

<h1> 게시물 작성 </h1>
<div>
    <form onsubmit="handleArticleSubmit(this)" action="${pageContext.request.contextPath}/usr/article/write"
          method="post">
        <div>
            <label for="title">제목:</label>
            <input type="text" id="title" name="title" required>
        </div>
        <div>
            <label for="content">내용:</label>
            <textarea id="content" name="content" required></textarea>
        </div>
        <div>
            <button type="submit">작성하기</button>
            <button type="button" onclick="history.back()">취소</button>
        </div>
    </form>
</div>