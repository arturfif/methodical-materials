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

<jsp:include page="footer.jsp"/>


<div>
    <h3>${success}</h3>
    <h2>Создать аккаунт для студента</h2>
    <form:form method="POST" modelAttribute="studentDto" action="add">
        <table>
            <tr>
                <td><form:label path="surname">Фамилия:</form:label></td>
                <td><form:input path="surname" size="50"/></td>
                <td><form:errors path="surname"/></td>
            </tr>
            <tr>
                <td><form:label path="name">Имя:</form:label></td>
                <td><form:input path="name" size="50"/></td>
                <td><form:errors path="name" cssClass="error"/></td>
            </tr>
            <tr>
                <td><form:label path="patronymic">Отчество:</form:label></td>
                <td><form:input path="patronymic" size="50"/></td>
                <td><form:errors path="patronymic"/></td>
            </tr>
            <tr>
                <td><form:label path="username">Логин:</form:label></td>
                <td><form:input path="username" size="50"/></td>
                <td><form:errors path="username" cssClass="error"/>${usernameError}</td>
            </tr>
            <tr>
                <td><form:label path="password">Пароль:</form:label></td>
                <td><form:password path="password" size="50"/>
                <td><form:errors path="password"/></td>
            </tr>
            <tr>
                <td><form:label path="confirmPassword">Повторите пароль:</form:label></td>
                <td><form:password path="confirmPassword" size="50"/>
                <td><form:errors path="confirmPassword"/>${passwordError}</td>
            </tr>
            <%--<tr>
                <td>
                    <label for="faculty">Факультет:</label>
                    <select id="faculty">
                        <c:forEach var="item" items="${facultyList}">
                            <option value="${item.id}">${item.name}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>--%>
            <tr>
                <td><label for="specialty">Специальность</label></td>
                <td>
                    <select name="specialtyId" id="specialty">
                        <c:forEach var="item" items="${specialtyList}">
                            <option value="${item.id}">${item.name}</option>
                        </c:forEach>
                    </select>
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
