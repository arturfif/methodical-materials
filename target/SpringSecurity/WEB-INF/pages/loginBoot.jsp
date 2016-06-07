<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Авторизация</title>
    <style>
        .error {
            padding: 15px;
            margin-bottom: 20px;
            border: 1px solid transparent;
            border-radius: 4px;
            color: #a94442;
            background-color: #f2dede;
            border-color: #ebccd1;
        }

        .msg {
            padding: 15px;
            margin-bottom: 20px;
            border: 1px solid transparent;
            border-radius: 4px;
            color: #31708f;
            background-color: #d9edf7;
            border-color: #bce8f1;
        }

        #login-box {
            width: 300px;
            padding: 20px;
            margin: 100px auto;
            background: #fff;
            -webkit-border-radius: 2px;
            -moz-border-radius: 2px;
            border: 1px solid #000;
        }
    </style>
    <jsp:include page="head.jsp"/>
</head>
<body onload='document.loginForm.username.focus();'>
    <jsp:include page="footer.jsp"/>

    <form class="form-singin" name='loginForm'
          action="<c:url value='/j_spring_security_check' />" method='POST'>
        <h2 class="form-signin-heading">Учетная запись</h2>
        <label for="inputLogin" class="sr-only">Логин</label>
        <input type='text' name='username' id="inputLogin" class="form-control" placeholder="Логин" required autofocus>
        <label for="inputPassword" class="sr-only">Пароль</label>
        <input type="password" name='password' id="inputPassword" class="form-control" placeholder="Пароль" required>
        <input name="submit" type="submit" value="Войти" class="btn btn-lg btn-primary btn-block"/>
        <input type="hidden" name="${_csrf.parameterName}"
               value="${_csrf.token}" />
        <a href="<c:url value="/"/>"><span class="glyphicon glyphicon-arrow-left"></span> На главную</a>

    </form>

    <c:if test="${not empty error}">
        <div class="alert alert-warning">${error}</div>
    </c:if>
    <c:if test="${not empty msg}">
        <div class="alert alert-success">${msg}</div>
    </c:if>

</body>
</html>

