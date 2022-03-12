<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ include file="layout/header.jsp"%>

<div class="container">
    <form action="/board/search" method="get" class="form-inline d-flex justify-content-end">
        <input type="text" id="word" name="word" class="form-control form-control-sm" style="margin: 10px;">
        <button class="btn btn-outline-info btn-sm">검색</button>
    </form>
    <c:forEach var="board" items="${boards.content}">
    <div class="card m-2">
        <div class="card-body">
            <h4 class="card-title">${board.title}</h4>
            <a href="/board/${board.id}" class="btn btn-primary">상세보기</a>
        </div>
    </div>
    </c:forEach>
    <ul class="pagination justify-content-center">
        <c:choose>
            <c:when test="${boards.first}">
        <li class="page-item disabled"><a class="page-link" href="?page=${boards.number-1}">Previous</a></li>
            </c:when>
            <c:otherwise>
                <li class="page-item"><a class="page-link" href="?page=${boards.number-1}">Previous</a></li>
            </c:otherwise>
        </c:choose>

        <c:choose>
            <c:when test="${boards.last}">
                <li class="page-item disabled"><a class="page-link" href="?page=${boards.number+1}">Next</a></li>
            </c:when>
            <c:otherwise>
                <li class="page-item"><a class="page-link" href="?page=${boards.number+1}">Next</a></li>
            </c:otherwise>
        </c:choose>
    </ul>

</div>

<%@ include file="layout/footer.jsp"%>
