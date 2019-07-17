<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
    <%@include file='css/style.css'%>
</style>
<html>
<head>
    <title>Products page</title>
</head>
<body>
<div align="center">
    <table>
        <tr>
            <th>Name</th>
            <th>Description</th>
            <th>Price</th>
            <th class="td-button">
                <form action="/admin/add/product" method="get">
                    <input type="submit" value="Add product">
                </form>
            </th>
        </tr>
        <c:forEach var="product" items="${products}">
            <tr>
                <td>${product.name}</td>
                <td>${product.price}</td>
                <td>${product.description}</td>
                <td class="td-button">
                    <form action="/admin/edit/product" method="get">
                        <input type="hidden" name="productID" value="${product.productID}">
                        <button type="submit">Edit</button>
                    </form>
                </td>
                <td class="td-button">
                    <form action="/admin/delete/product" method="post">
                        <input type="hidden" name="productID" value="${product.productID}">
                        <button type="submit">Delete</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
    <h4>${message}</h4>
    <form>
        <input type="submit" name="submit" formaction="/admin/users" formmethod="post" value="Users page">
    </form>
</div>
</body>
</html>
