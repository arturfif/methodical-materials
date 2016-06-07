<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<body>
	<h1> ${title}</h1>
	<h1> ${message}</h1>

	<sec:authorize access="isAuthenticated()">
		<!-- For login user -->
		<c:url value="/j_spring_security_logout" var="logoutUrl" />
		<form action="${logoutUrl}" method="post" id="logoutForm">
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		</form>
		<script>
			function formSubmit() {
				document.getElementById("logoutForm").submit();
			}
		</script>

		<c:if test="${pageContext.request.userPrincipal.name != null}">
			<h2>
				Пользователь : ${pageContext.request.userPrincipal.name} | <a
					href="javascript:formSubmit()">Выйти</a>
			</h2>
		</c:if>
	</sec:authorize>

	<sec:authorize access="isAnonymous()">
	<a href="<c:url value="/login"/>">Авторизоваться</a>
	</sec:authorize>
</body>
</html>