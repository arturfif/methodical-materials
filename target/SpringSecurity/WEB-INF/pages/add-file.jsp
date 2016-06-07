<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Добавить документ</title>
    <jsp:include page="head.jsp"/>
    <script>
        $(document).ready(function() {
            var max_fields      = 10; //maximum input boxes allowed
            var wrapper         = $(".input_fields_wrap"); //Fields wrapper
            var add_button      = $(".add_field_button"); //Add button ID

            var x = 1; //initial text box count
            $(add_button).click(function(e){ //on add input button click
                e.preventDefault();
                if(x < max_fields){ //max input box allowed
                    x++; //text box increment
                    $(wrapper).append('<div><input type="text" name="authorList"/><a href="#" class="remove_field">Удалить</a></div>'); //add input box
                }
            });

            $(wrapper).on("click",".remove_field", function(e){ //user click on remove text
                e.preventDefault(); $(this).parent('div').remove(); x--;
            })
        });
    </script>
</head>

<body>

<jsp:include page="footer.jsp"/>


<div>
    <h3>${success}</h3>
    <h2>Добавить документ в систему</h2>
    <form:form method="POST"  modelAttribute="documentDto"  action="add" enctype="multipart/form-data">
        <table>
            <tr>
                <td><form:label path="libraryKey">Библиотечный номер:</form:label></td>
                <td><form:input path="libraryKey"/></td>
                <td><form:errors path="libraryKey"/></td>
            </tr>
            <tr>
                <td><form:label path="name">Название:</form:label></td>
                <td><form:input path="name" size="50"/></td>
                <td><form:errors path="name" cssClass="error"/></td>
            </tr>
            <tr>
                <td><label for="department">Кафедра:</label></td>
                <td>
                    <select name="departmentId" id="department">
                        <c:forEach var="item" items="${departmentList}">
                            <option value="${item.id}">${item.name}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td><form:label path="publishingYear">Год издания:</form:label></td>
                <td><form:input path="publishingYear"/></td>
                <td><form:errors path="publishingYear" cssClass="error"/></td>
            </tr>
            <tr>
                <td><label>Авторы:</label></td>
                <td>
                    <div class="input_fields_wrap">
                    <button type="button" class="add_field_button">Добавить поле</button>
                    <div><input type="text" name="authorList"></div>
                    </div>
                </td>
            </tr>
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
                <td><label>Специальность</label></td>
                <td>
                    <select name="specialtyId">
                        <c:forEach var="item" items="${specialtyList}">
                            <option value="${item.id}">${item.name}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td><form:label path="documentPath">Документ:</form:label></td>
                <td><input type="file" name="documentPath" id="documentPath"/></td>
                <td><form:errors path="documentPath"/></td>
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
                <td>
                    <input type="submit" value="Создать аккаунт"/>
                </td>
            </tr>
        </table>
    </form:form>
</div>

</body>
</html>
