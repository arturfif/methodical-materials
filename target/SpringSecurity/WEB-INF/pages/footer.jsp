<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:url value="/j_spring_security_logout" var="logoutUrl"/>
<form action="${logoutUrl}" method="post" id="logoutForm">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
</form>

<nav class="navbar navbar-default navbar-static-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="<c:url value="/"/>">Система "Методические материалы"</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">

            <ul class="nav navbar-nav navbar-left">
                <sec:authorize access="isAuthenticated()">
                <li><a href="<c:url value="/search"/>">Поиск</a></li>
                </sec:authorize>
                <sec:authorize access="hasRole('STUDENT')">
                <li><a href="">Методические материалы по специальности</a></li>
                </sec:authorize>
                <sec:authorize access="hasAnyRole('ADMIN', 'TEACHER')">
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">Документы
                        <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="<c:url value="/document/my"/>">Мои документы</a></li>
                        <li><a href="<c:url value="/document/add"/>">Добавить документ</a></li>
                        <sec:authorize access="hasRole('ADMIN')">
                        <li><a href="<c:url value="/document/check"/>">Проверить документы</a></li>
                        </sec:authorize>
                    </ul>
                </li>
                </sec:authorize>
                <sec:authorize access="hasRole('ADMIN')">
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">Создать аккаунт
                        <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="<c:url value="/admin/account/admin/add"/>">Для администратора</a></li>
                        <li><a href="<c:url value="/admin/account/student/add"/>">Для студента</a></li>
                    </ul>
                </li>
                </sec:authorize>
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">Скачать
                        <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="#">Автономную версию</a></li>
                        <li><a href="#">Базу материалов</a></li>
                    </ul>
                </li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <sec:authorize access="isAnonymous()">
                    <li><a href="<c:url value="/login"/>">Войти</a></li>
                </sec:authorize>
                <sec:authorize access="isAuthenticated()">
                    <li>
                        <a href="javascript:document.getElementById('logoutForm').submit();">Выйти</a>
                    </li>
                </sec:authorize>
            </ul>
        </div><!--/.nav-collapse -->
    </div>
</nav>

