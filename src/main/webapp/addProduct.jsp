<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style>
    <%@include file='css/style.css' %>
</style>
<html>
<head>
    <title>Add product</title>
</head>
<body>
<form action="addProduct" method="post" class="decor">
    <div class="form-left-decoration"></div>
    <div class="form-right-decoration"></div>
    <div class="circle"></div>
    <div class="form-inner">
        <h3>Add product:</h3><br>
        <input name="name" placeholder="Name" value="${productName}">
        <input name="price" type="number" placeholder="Price" value="${productPrice}">
        <textarea name="description" placeholder="Description" rows="3">${productDescription}</textarea>
        <input type="hidden" name="productId" value="${productId}">
        <h4>${error}</h4>
        <h4>${done}</h4>
        <form>
            <input type="submit" formaction="/add/product" formmethod="post" value="Do">
        </form>
        <form>
            <input type="submit" formaction="/products" formmethod="post" value="Products page">
        </form>
    </div>
</form>
</body>
</html>