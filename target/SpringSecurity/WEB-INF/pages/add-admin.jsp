<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Администрирование - Создать аккаунта для студента</title>
    <link href="<c:url value="/css/bootstrap.min.css"/>" rel="stylesheet">
</head>
<body>

<div>
    <h3>${success}</h3>
    <h2>Создать аккаунт для администратора</h2>
    <form:form method="POST" modelAttribute="userDto" action="add"> <!-- Связать форму -->
            <table>
                <tr>
                    <td><form:label path="surname">Фамилия:</form:label></td>
                    <td><form:input path="surname" size="50"/></td>
                </tr>
                <tr>
                    <td><form:label path="name">Имя:</form:label></td>
                    <td><form:input path="name" size="50"/></td>
                    <td><form:errors path="name" cssClass="error"/></td>
                </tr>
                <tr>
                    <td><form:label path="patronymic">Отчество:</form:label></td>
                    <td><form:input path="patronymic" size="50"/></td>
                </tr>
                <tr>
                    <td><form:label path="username">Логин:</form:label></td>
                    <td><form:input path="username" size="50"/></td>
                    <td><form:errors path="username" cssClass="error"/>${usernameError}</td>
                </tr>
                <tr>
                    <td><form:label path="password">Пароль:</form:label></td>
                    <td><form:password path="password" showPassword="true" size="50"/>
                    <td><form:errors path="password"/>${passwordError}</td>
                </tr>
                <tr>
                    <td><form:label path="confirmPassword">Повторите пароль:</form:label></td>
                    <td><form:password path="confirmPassword" size="50"/>
                    <td><form:errors path="confirmPassword"/></td>
                </tr>
                <tr>
                    <td><form:label path="roleId">Роль:</form:label></td>
                    <td>
                        <form:radiobutton path="roleId" checked="checked" value="2"/>Преподаватель
                        <form:radiobutton path="roleId" value="1"/>Администратор
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="submit" value="Создать аккаунт"/>
                    </td>
                </tr>
            </table>
    </form:form>
</div>

<script src="<c:url value="/js/bootstrap.min.js"/>"></script>
</body>
</html>
