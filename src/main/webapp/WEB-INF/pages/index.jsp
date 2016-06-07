<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Главная страница</title>
    <jsp:include page="head.jsp"/>

</head>
<body>
<jsp:include page="footer.jsp"/>

<div class="container">
    <form class="form-signin" name='loginForm'
          action="<c:url value='/j_spring_security_check' />" method='POST'>
        <h2 class="form-signin-heading">Учетная запись</h2>
        <label for="inputLogin" class="sr-only">Логин</label>
        <input type='text' name='username' id="inputLogin" class="form-control" placeholder="Логин" required autofocus>
        <label for="inputPassword" class="sr-only">Пароль</label>
        <input type="password" name='password' id="inputPassword" class="form-control" placeholder="Пароль" required>
        <input name="submit" type="submit" value="Войти" class="btn btn-lg btn-primary btn-block"/>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <a href="<c:url value="/"/>"><span class="glyphicon glyphicon-arrow-left"></span> На главную</a>

        <c:if test="${not empty error}">
            <div class="alert alert-danger">${error}</div>
        </c:if>
        <c:if test="${not empty msg}">
            <div class="alert alert-success">${msg}</div>
        </c:if>
    </form>

</div>
</body>
</html>
