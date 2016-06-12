<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>
<head>
    <title>Проверка документов</title>
    <jsp:include page="../public/service/head.jsp"/>

</head>

<body>

<jsp:include page="../public/service/footer.jsp"/>



<div class="container">
    <h3>Непринятые документы</h3>
    <table class="table table-striped table-hover">
        <thead>
        <tr>
            <th>Библ. №</th>
            <th>Название документа</th>
            <th>Авторы</th>
            <th>Год издания</th>
            <th>Кафедра</th>
            <th>Загрузил(а)</th>
            <th>Скачать</th>
            <th>Принять</th>
            <th>Отклонить</th>
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
                <td>${document.user.surname} ${document.user.name} ${document.user.patronymic}</td>
                <td><a class="btn btn-default btn-xs" href="https://drive.google.com/open?id=${document.objectKey}">Скачать</a></td>
                <td>
                    <form:form method="POST" action="accept">
                        <input type="hidden" name="acceptId" value="${document.id}"/>
                        <input type="submit" value="Принять" class="btn btn-success btn-xs"/>
                    </form:form>
                </td>
                <td>
                    <form:form method="POST" action="deny">
                        <input type="hidden" name="denyId" value="${document.id}"/>
                        <input type="submit" value="Отклонить" class="btn btn-danger btn-xs"/>
                    </form:form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
