<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ include file="layout/header.jsp" %>

<div class="container">
    <form action="/" class="form-inline d-flex justify-content-end" method="GET">
        <select id="field" name="field" class="form-control form-control-sm">
            <option value="제목">제목</option>
            <option value="내용">내용</option>
            <option value="tc">제목+내용</option>
        </select>
        <input type="text" id="word" name="word" class="form-control form-control-sm" style="margin: 10px" placeholder="검색어를 입력하세요.">
        <input type="submit" class="btn btn-outline-info btn-sm"  value="검색">
    </form>

<%--    <form action="/board/search" method="get" class="form-inline d-flex justify-content-end">--%>
<%--        <input type="text" id="word" name="word" class="form-control form-control-sm" style="margin: 10px;">--%>
<%--        <button class="btn btn-outline-info btn-sm">검색</button>--%>
<%--    </form>--%>
    <c:forEach var="board" items="${boards.content}">
        <div class="card m-2">
            <div class="card-body">
                <h4 class="card-title">${board.title}</h4>
                <a href="/board/${board.id}" class="btn btn-primary">상세보기</a>
            </div>
        </div>
    </c:forEach>
    <ul class="pagination justify-content-center">
        <li class="page-item"><a class="page-link" href="?page=${boards.first}"><<</a></li>
        <c:choose>
            <c:when test="${boards.first}">
                <li class="page-item disabled"><a class="page-link" href="?page=${boards.number-1}">Previous</a></li>
            </c:when>
            <c:otherwise>
                <li class="page-item"><a class="page-link" href="?page=${boards.number-1}">Previous</a></li>
            </c:otherwise>
        </c:choose>


        <c:forEach var="page" begin="0" end="${boards.totalPages-1}" step="1">
            <li class="page-item"> <a class="page-link" href="?page=${page}">${page+1}</a></li>
        </c:forEach>

        <c:choose>
            <c:when test="${boards.last}">
                <li class="page-item disabled"><a class="page-link" href="?page=${boards.number+1}">Next</a></li>
            </c:when>
            <c:otherwise>
                <li class="page-item"><a class="page-link" href="?page=${boards.number+1}">Next</a></li>
            </c:otherwise>
        </c:choose>

        <li class="page-item"><a class="page-link" href="?page=${boards.totalPages-1}">>></a></li>
    </ul>
</div>


<%@ include file="layout/footer.jsp" %>