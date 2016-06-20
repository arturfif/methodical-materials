<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Главная страница</title>

    <jsp:include page="service/head.jsp"/>
</head>
<body>
<jsp:include page="service/header.jsp"/>

<div class="container">
    <sec:authorize access="isAnonymous()">
    <div class="alert alert-success">Для работы с системой
        "Методические материалы" Вам необходимо
        <a href="<c:url value="/login"/>">авторизироваться</a>.
    </div>
    </sec:authorize>
    <c:if test="${not empty error}">
        <div class="alert alert-danger">${error}</div>
    </c:if>
    <sec:authorize access="isAuthenticated()">
        <div class="alert alert-info">Вы авторизированы. Ваш логин: ${pageContext.request.userPrincipal.name}</div>
    </sec:authorize>
    <div class="alert alert-info">
        Система "Методические материалы" — сервис, предоставляющий доступ к учебным материалам университета ДНУЖТ.
    </div>
</div>
</body>
</html>
