<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style>
    <%@include file='css/style.css'%>
</style>
<html>
<head>
    <title>Users</title>
</head>
<body>
<div align="center">
    <table>
        <tr>
            <th>Email</th>
            <th>Password</th>
            <th class="td-button">
                <form action="/admin/add/user" method="get">
                    <input type="submit" value="Add user">
                </form>
            </th>
        </tr>
        <c:forEach var="user" items="${users}">
            <tr>
                <td>${user.email}</td>
                <td>${user.password}</td>
                <td>${user.role.role}</td>
                <td class="td-button">
                    <form action="/admin/edit/user" method="get">
                        <input type="hidden" name="userId" value="${user.userId}">
                        <button type="submit">Edit</button>
                    </form>
                </td>
                <td class="td-button">
                    <form action="/delete/user" method="post">
                        <input type="hidden" name="userId" value="${user.userId}">
                        <button type="submit">Delete</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
    <h4>${message}</h4>
    <form>
        <input type="submit" formaction="/products" formmethod="post" value="Products page">
    </form>
</div>
</body>
</html>
