<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<html lang="ru">
<head>
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h3>Meals</h3>
<table border="1">
    <thead>
    <tr>
        <th>#</th>
        <th>DATE</th>
        <th>TIME</th>
        <th>DESCRIPTION</th>
        <th>CALORY</th>
    </tr>
    </thead>
    <tbody>

    <jsp:useBean id="mealsTo" class="java.util.ArrayList" scope="request"/>

    <c:forEach items="${mealsTo}" var="cell">
        <c:set var="color" value="ffffff"/>
        <c:if test="${cell.excess}">
            <c:set var="color" value="#f08080"/>
        </c:if>
        <tr bgcolor=${color}>
        <td></td>
        <td align="left">${cell.date} </td>
        <td align="left" >${cell.time} </td>
        <td align="left">${cell.description} </td>
        <td align="center">${cell.calories}</td>
        </tr>
    </c:forEach>


    </tbody>
</table>
</body>
</html>