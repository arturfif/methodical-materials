<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page session="true" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Администрирование</title>
    <link href="<c:url value="/css/bootstrap.min.css"/>" rel="stylesheet">
</head>
<body>
<h1>${title}</h1>
<h1>${message}</h1>

<c:url value="/j_spring_security_logout" var="logoutUrl"/>
<form action="${logoutUrl}" method="post" id="logoutForm">
    <input type="hidden" name="${_csrf.parameterName}"
           value="${_csrf.token}"/>
</form>
<script>
    function formSubmit() {
        document.getElementById("logoutForm").submit();
    }
</script>

<c:if test="${pageContext.request.userPrincipal.name != null}">
    <h2>
        Добро пожаловать : ${pageContext.request.userPrincipal.name} | <a
            href="javascript:formSubmit()">Выйти</a>
    </h2>
</c:if>
<a href="<c:url value="/admin/account/student/add"/>">Создать аккаунт для студента</a>
<br/>
<a href="<c:url value="/admin/account/admin/add"/>">Создать аккаунт для администратора</a>
<br/>
<a href="<c:url value="/admin/file/add"/>">Добавить документ в систему</a>
<script src="<c:url value="/js/bootstrap.min.js"/>"></script>
</body>
</html>