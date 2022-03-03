<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ include file="../layout/header.jsp"%>

<div class="container">
    <form>
        <input type="hidden" id="id" value="${board.id}">
        <div class="form-group">
            <input value="${board.title}" type="text" name="title" class="form-control" placeholder="Enter title" id="title">
        </div>

        <div class="form-group">
            <textarea class="form-control summernote" rows="5" id="content">${board.content}</textarea>
        </div>
    </form>
        <button id="btn-update" class="btn btn-primary">수정</button>
</div>

<script>
    $(".summernote").summernote({
        placeholder : "게시글 내용을 입력하세요",
        tabSize :2,
        height : 100
    })
</script>
<script src="/js/board.js">
</script>
<%@ include file="../layout/footer.jsp"%>
