<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style>
    <%@include file='css/style.css'%>
</style>
<html>
<head>
    <title>Users</title>
</head>
<header>
    <h1 align="center">Users ¯\_(ツ)_/¯</h1>
</header>
<body>
<div align="center">
    <table>
        <th class="td-button">
            <form>
                <input class="add" type="submit" formaction="/products" formmethod="post" value="Products page">
            </form>
        </th>
        <th class="td-button">
            <form action="/admin/add/user" method="get">
                <input class="add" type="submit" value="Add user">
            </form>
        </th>
        <tr>
            <th>Email</th>
            <th>Password</th>
            <th>Role</th>
        </tr>
        <c:forEach var="user" items="${users}">
            <tr>
                <td>${user.email}</td>
                <td>${user.password}</td>
                <td>${user.role.name}</td>
                <td class="td-button">
                    <form action="/admin/edit/user" method="post">
                        <input type="hidden" name="userID" value="${user.userID}">
                        <button class="edit" type="submit">Edit</button>
                    </form>
                </td>
                <td class="td-button">
                    <form action="/delete/user" method="post">
                        <input type="hidden" name="userID" value="${user.userID}">
                        <button class="delete" type="submit">Delete</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
    <h4>${message}</h4>
</div>
</body>
</html>
