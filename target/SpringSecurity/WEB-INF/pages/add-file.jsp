<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Добавить документ</title>
    <jsp:include page="head.jsp"/>
    <script src="<c:url value="/js/author-list.js"/>"></script>
    <script src="<c:url value="/js/specialty-list.js"/>"></script>
    <style>
        .form-signin {
            max-width: 420px;
            padding: 15px;
            margin: 0 auto;
        }
    </style>
</head>

<body>

<jsp:include page="footer.jsp"/>


<div class="container">
    <c:if test="${not empty success}">
        <div class="alert alert-success">${success}</div>
    </c:if>
    <c:if test="${not empty error}">
        <div class="alert alert-danger">${error}</div>
    </c:if>
    <form:form cssClass="form-horizontal form-signin" method="POST" modelAttribute="documentDto" action="add" enctype="multipart/form-data">
        <h2>Добавить документ в систему</h2>

        <div class="form-group">
            <form:label path="libraryKey">Библиотечный номер:</form:label>
            <form:input cssClass="form-control" path="libraryKey"/>
            <form:errors path="libraryKey"/>
        </div>

        <div class="form-group">
            <form:label path="name">Название документа:</form:label>
            <form:input cssClass="form-control" path="name"/>
            <form:errors path="name"/>
        </div>

        <div class="form-group">
            <form:label path="departmentId">Кафедра:</form:label>
            <select name="departmentId" class="form-control" id="departmentId">
                <c:forEach var="item" items="${departmentList}">
                    <option value="${item.id}">${item.name}</option>
                </c:forEach>
            </select>
            <form:errors path="departmentId"/>
        </div>

        <div class="form-group">
            <form:label path="publishingYear">Год издания:</form:label>
            <form:input cssClass="form-control" path="publishingYear"/>
            <form:errors path="publishingYear"/>
        </div>

        <div class="form-group">
            <div class="input_authors_wrap">
                <label>Авторы:</label>
                <br>
                <button type="button" class="btn btn-default add_author_button">Добавить автора</button>
                <br><br>
                <div><input type="text" class="form-control"
                            pattern="([а-яА-Я'ҐґЄєЇїІі-]){1,50} (([а-яА-Я'ҐґЄєЇїІі-]){1}.){1,2}"
                            placeholder="Формат: Фамилия И.О. или Фамилия И." name="authorSet"></div>
                <br>
            </div>
        </div>

        <div class="form-group">
            <div class="input_specialties_wrap">
                <label>Специальности:</label>
                <br>
                <button type="button" class="btn btn-default" id="doit">Добавить специальность</button>
                <br><br>
                <div>
                <select name="specialtySet" class="form-control" id="specialty1">
                    <c:forEach var="item" items="${specialtyList}">
                        <option value="${item.id}">${item.name}</option>
                    </c:forEach>
                </select> <%--  TODO:  REMOVE SPECIALTY  --%>
                </div>
                <br>
            </div>
        </div>

        <div class="form-group">
            <form:label path="file">Файл:</form:label>
            <input type="file" name="file" class="form-control"  id="file"/>
            <form:errors path="file"/>
        </div>

        <div class="form-group">
        <input type="submit" class="btn btn-primary" value="Добавить документ"/>
        </div>

    </form:form>
</div>

</body>
</html>
