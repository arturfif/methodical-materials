<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <title>Поиск</title>
    <jsp:include page="service/head.jsp"/>
</head>
<body>
<jsp:include page="service/header.jsp"/>

<div class="container">
    <form:form method="GET" action="search">
        <div class="row">
            <div class="col-lg-7">
                <div class="input-group">
                    <input type="text" name="searchQuery"
                           <c:if test="${not empty searchQuery}">value="${searchQuery}"</c:if>
                           class="form-control" required title="Сначала введите поисковый запрос"
                           placeholder="Библиотечный номер; название документа, кафедры; фамилию автора; год издания">
            <span class="input-group-btn">
              <button class="btn btn-default" type="submit">Поиск</button>
            </span>

                </div><!-- /input-group -->
            </div><!-- /.col-lg-6 -->
        </div>
        <!-- /.row -->
    </form:form>
</div>
<div class="container">
    <h3>${searchTitle}</h3>
    <table class="table table-striped table-hover">
        <thead>
        <tr>
            <th>Библ. №</th>
            <th>Название документа</th>
            <th>Авторы</th>
            <th>Год издания</th>
            <th>Кафедра</th>
            <th>Ссылка</th>
            <sec:authorize access="hasRole('ADMIN')">
                <th>Загрузил(а)</th>
                <th>Удалить</th>
            </sec:authorize>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${documentList}" var="document">
            <tr>
                <td>${document.libraryKey}</td>
                <td>${document.name}</td>
                <td>
                    <c:forEach items="${document.authorSet}" var="author">
                        ${author.surname} ${author.name}${author.patronymic}
                    </c:forEach>
                </td>
                <td>${document.publishingYear}</td>
                <td>${document.department.name}</td>
                <td><a class="btn btn-default btn-xs" target="_blank" href="https://drive.google.com/open?id=${document.objectKey}">Просмотреть</a>
                </td>
                <sec:authorize access="hasRole('ADMIN')">
                    <td>${document.user.surname} ${document.user.name} ${document.user.patronymic}</td>
                    <td>
                        <form:form method="POST" action="deny">
                            <input type="hidden" name="denyId" value="${document.id}"/>
                            <input type="submit" value="Удалить" class="btn btn-danger btn-xs"/>
                        </form:form>
                    </td>
                </sec:authorize>
                    <%-- TODO remove  --%>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>

