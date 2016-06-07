<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<body>
<h1>Статус 403 - Доступ запрещен</h1>

<c:choose>
	<c:when test="${empty username}">
		<h2>У Вас нет прав досутпа для просмотра этой страницы!</h2>
	</c:when>
	<c:otherwise>
		<h2>Пользователь : ${username} <br/>У Вас нет прав досутпа для просмотра этой страницы!</h2>
	</c:otherwise>
</c:choose>

</body>
</html>