<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Аккаунт для студента</title>
    <jsp:include page="../public/service/head.jsp"/>
    <script src="<c:url value="/js/faculty-specialty.js"/>"></script>
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

    <form:form method="POST" cssClass="form-horizontal form-signin" modelAttribute="studentDto" action="add">

        <div class="form-group"><h3 style="float: none; margin: 0 auto;">Создать аккаунт для студента</h3></div>

        <div class="form-group">
            <form:label path="surname">Фамилия:</form:label>
            <form:input cssClass="form-control" path="surname" size="50"/>
            <form:errors path="surname"/>
        </div>
        <div class="form-group">
            <form:label path="name">Имя:</form:label>
            <form:input cssClass="form-control" path="name" size="50"/>
            <form:errors path="name" cssClass="error"/>
        </div>
        <div class="form-group">
            <form:label path="patronymic">Отчество:</form:label>
            <form:input cssClass="form-control" path="patronymic" size="50"/>
            <form:errors path="patronymic"/>
        </div>
        <div class="form-group">
            <form:label path="username">Логин:</form:label>
            <form:input cssClass="form-control" path="username" size="50"/>
            <form:errors path="username" cssClass="error"/>${usernameError}
        </div>
        <div class="form-group">
            <form:label path="password">Пароль:</form:label>
            <form:password cssClass="form-control" path="password" size="50"/>
            <form:errors path="password"/>
        </div>
        <div class="form-group">
            <form:label path="confirmPassword">Повторите пароль:</form:label>
            <form:password cssClass="form-control" path="confirmPassword" size="50"/>
            <form:errors path="confirmPassword"/>${passwordError}
        </div>
        <div class="form-group">
            <label for="faculty">Факультет:</label>
            <select id="faculty" class="form-control">
                <c:forEach var="item" items="${facultyList}">
                    <option value="${item.id}">${item.name}</option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group">
            <label for="specialty">Специальность:</label>

            <select name="specialtyId" id="specialty" class="form-control">
                <c:forEach var="item" items="${specialtyList}">
                    <option title="${item.faculty.id}"
                            value="${item.id}" ${facultyList[0].id != item.faculty.id ? 'hidden="hidden"' : ''}>
                            ${item.name}
                    </option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group">
            <input type="submit" class="btn btn-primary form-control" value="Создать аккаунт"/>
        </div>


    </form:form>
</div>
</body>
</html>
