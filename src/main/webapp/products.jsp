<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
    <%@include file='css/style.css'%>
</style>
<html>
<head>
    <title>Products page</title>
</head>
<header>
    <h1 align="center">Products ¯\_(ツ)_/¯</h1>
</header>
<body>
<div align="center">
    <table>
        <th class="td-button">
            <form>
                <input class="add" type="submit" name="submit"
                       formaction="/admin/users" formmethod="post"
                       value="Users page">
            </form>
        </th>
        <th class="td-button">
            <form action="/add/product" method="get">
                <input class="add" type="submit" value="Add product">
            </form>
        </th>
        <tr>
            <th>Name</th>
            <th>Description</th>
            <th>Price</th>
        </tr>
        <c:forEach var="product" items="${products}">
            <tr>
                <td>${product.name}</td>
                <td>${product.price}</td>
                <td>${product.description}</td>
                <td class="td-button">
                    <form action="/edit/product" method="post">
                        <input type="hidden" name="productID" value="${product.productID}">
                        <button class="edit" type="submit">Edit</button>
                    </form>
                </td>
                <td class="td-button">
                    <form action="/delete/product" method="post">
                        <input type="hidden" name="productID" value="${product.productID}">
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
