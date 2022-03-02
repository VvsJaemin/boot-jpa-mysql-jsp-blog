<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ include file="../layout/header.jsp"%>

<div class="container">
    <form>
        <div class="form-group">
            <label for="title">Title</label>
            <input type="text" name="title" class="form-control" placeholder="Enter title" id="title">
        </div>

        <div class="form-group">
            <label for="content">Content</label>
            <textarea class="form-control summernote" rows="5" id="content"></textarea>
        </div>
    </form>
        <button id="btn-save" class="btn btn-primary">등록</button>
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
