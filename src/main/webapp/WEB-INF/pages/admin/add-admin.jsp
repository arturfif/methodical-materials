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
    <script src="<c:url value="/js/custom-validity.js"/>"></script>
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
    <h3>${success}</h3>
    <form:form method="POST" id="admin-form" cssClass="form-horizontal form-signin" modelAttribute="userDto"
               action="add"> <!-- Связать форму -->
        <h3 style="float: none; margin: 0 auto;">Создать аккаунт для администратора</h3>

        <div class="form-group">
            <form:label path="surname">Фамилия:</form:label>
            <form:input required="required" name="surname" cssClass="form-control" path="surname" size="50"/>
            <div id="surnameError" class="alert alert-danger hidden-field"></div>
        </div>
        <div class="form-group">
            <form:label path="name">Имя:</form:label>
            <form:input required="required" name="name" cssClass="form-control" path="name" size="50"/>
            <div id="nameError" class="bg-danger"></div>
            <form:errors path="name" cssClass="error"/>
        </div>
        <div class="form-group">
            <form:label path="patronymic">Отчество:</form:label>
            <form:input cssClass="form-control" path="patronymic" size="50"/>
        </div>
        <div class="form-group">
            <form:label path="username">Логин:</form:label>
            <form:input required="required" path="username" cssClass="form-control" name="username" size="50"/>
            <form:errors path="username" cssClass="error"/>${usernameError}
        </div>
        <div class="form-group">
            <form:label path="password">Пароль:</form:label>
            <form:password required="required" cssClass="form-control" name="password" path="password"
                           showPassword="true" size="50"/>
            <form:errors path="password"/>${passwordError}
        </div>
        <div class="form-group">
            <form:label path="confirmPassword">Повторите пароль:</form:label>
            <form:password required="required" cssClass="form-control" name="confirmPassword" path="confirmPassword"
                           size="50"/>
            <form:errors path="confirmPassword"/>
        </div>
        <form:label path="roleId">Роль:</form:label>
        <div class="radio">
            <label><form:radiobutton path="roleId" checked="checked" value="2"/>
                Администратор (загруженные документы требуют подтверждения)
            </label>
        </div>
        <div class="radio">
            <label><form:radiobutton path="roleId" value="1"/>
                Супер администратор (полный доступ)
            </label>
        </div>

        <div class="form-group">
            <input type="submit" class="btn btn-primary form-control" value="Создать аккаунт"/>
        </div>

    </form:form>
</div>

</body>
</html>
