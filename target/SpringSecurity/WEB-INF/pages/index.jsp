<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Главная страница</title>

    <jsp:include page="head.jsp"/>
</head>
<body>
<jsp:include page="footer.jsp"/>

<div class="container">
    <sec:authorize access="isAnonymous()">
    <div class="alert alert-success">Для работы с системой
        "Методические материалы" Вам необходимо
        <a href="<c:url value="/login"/>">авторизироваться</a>.
    </div>
    </sec:authorize>
    <sec:authorize access="isAuthenticated()">
        <div class="alert alert-info">Вы авторизированы. Ваш логин: ${pageContext.request.userPrincipal.name}</div>
    </sec:authorize>
</div>
</body>
</html>
