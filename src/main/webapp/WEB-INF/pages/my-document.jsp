<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>
<head>
    <title>Мои документы</title>
    <jsp:include page="head.jsp"/>

</head>

<body>

<jsp:include page="footer.jsp"/>



<div class="container">
    <h3>Мои документы</h3>
    <table class="table table-striped table-hover">
        <thead>
        <tr>
            <th>Библ. №</th>
            <th>Название документа</th>
            <th>Авторы</th>
            <th>Год издания</th>
            <th>Кафедра</th>
            <th>Ссылка</th>
            <th>Удалить</th>
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
                <td><a href="https://drive.google.com/open?id=${document.objectKey}">Скачать</a></td>
                <td><a href="<c:url value="/document/add"/>">Удалить</a></td>
                <%-- TODO remove --%>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>