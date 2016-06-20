<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Аккаунт для администратора</title>
    <jsp:include page="../public/service/head.jsp"/>
    <style>
        .form-signin {
            max-width: 420px;
            padding: 15px;
            margin: 0 auto;
        }
    </style>
</head>
<body>

<jsp:include page="../public/service/header.jsp"/>

<div class="container">

    <c:if test="${not empty success}">
        <div class="alert alert-success">${success}</div>
    </c:if>

    <form:form method="POST" id="admin-form" cssClass="form-horizontal form-signin" modelAttribute="userDto"
               action="add"> <!-- Связать форму -->
        <div class="form-group"><h3 style="float: none; margin: 0 auto;">Аккаунт для администратора</h3></div>

        <div class="form-group">
            <form:label path="surname">Фамилия:</form:label>
            <form:input required="required" pattern="^[а-яА-Я- ]{1,50}$" cssClass="form-control" path="surname"
                        size="50"/>
        </div>
        <div class="form-group">
            <form:label path="name">Имя:</form:label>
            <form:input required="required" pattern="^[а-яА-Я- ]{1,50}$" cssClass="form-control" path="name" size="50"/>
        </div>
        <div class="form-group">
            <form:label path="patronymic">Отчество:</form:label>
            <form:input cssClass="form-control" pattern="^[а-яА-Я- ]{0,50}$" path="patronymic" size="50"/>
        </div>
        <div class="form-group">
            <form:label path="username">Логин:</form:label>
            <form:input required="required" pattern="^[a-zA-Z0-9-]{3,50}$"
                        path="username" cssClass="form-control" size="50"/>
                ${usernameError}
        </div>
        <div class="form-group">
            <form:label path="password">Пароль:</form:label>
            <form:password required="required" cssClass="form-control" path="password"
                           pattern=".{6,20}$"
                           showPassword="false" size="20"/>
                ${passwordError}
        </div>
        <div class="form-group">
            <form:label path="confirmPassword">Повторите пароль:</form:label>
            <form:password required="required" pattern=".{6,20}$" cssClass="form-control" path="confirmPassword"
                           size="20"/>
        </div>
        <form:label path="roleId">Роль:</form:label>
        <div class="form-group">
            <div class="radio">
                <label><form:radiobutton path="roleId" checked="checked" value="2"/>
                    Преподаватель (загруженные документы требуют подтверждения)
                </label>
            </div>
            <div class="radio">
                <label><form:radiobutton path="roleId" value="1"/>
                    Администратор (полный доступ)
                </label>
            </div>
        </div>
        <div class="form-group">
            <input type="submit" class="btn btn-primary form-control" value="Создать аккаунт"/>
        </div>

    </form:form>
</div>

</body>
</html>
