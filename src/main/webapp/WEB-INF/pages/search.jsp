<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Поиск</title>
    <jsp:include page="head.jsp"/>
</head>
<body>
<jsp:include page="footer.jsp"/>

<div class="container">
    <form:form method="GET" action="search">
        <div class="row">
            <div class="col-lg-7">
                <div class="input-group">

                    <input type="text" name="searchQuery" class="form-control"
                           placeholder="Введите библиотечный номер; название документа, кафедры; фамилию автора">
            <span class="input-group-btn">
              <button class="btn btn-default" type="submit">Поиск</button>
            </span>

                </div><!-- /input-group -->
            </div><!-- /.col-lg-6 -->
        </div>
        <!-- /.row -->
    </form:form>

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
                            ${document.surname} &nbsp;
                        </c:forEach>
                    </td>
                    <td>${document.publishingYear}</td>
                    <td>${document.department}</td>
                    <td><a href="https://drive.google.com/open?id=${document.objectKey}">Скачать</a></td>

                </tr>
            </c:forEach>
        </tbody>
    </table>

</div>
</body>
</html>

