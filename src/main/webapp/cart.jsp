<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<style>
    <%@include file='css/style.css'%>
</style>
<html>
<head>
    <title>Cart</title>
</head>
<header>
    <h1 align="center">Your cart ¯\_(ツ)_/¯</h1>
    <form>
        <button class="cart-toggle"
                type="submit"
                name="submit"
                formaction="/store/cart" formmethod="post"
                value="Cart page">${cardsCounter}
        </button>
    </form>
</header>
<body>
<div name="cart" align="center">
    <table>
        <tr>
            <th>Name</th>
            <th>Price</th>
            <th>Description</th>
            </th>
        </tr>
<c:set var = "check" scope = "page" value = "${flag}"/>
<c:if test = "${check > 0}">
        <c:forEach var="product" items="${order}">
            <tr>
                <td>${product.name}</td>
                <td>${product.price}</td>
                <td>${product.description}</td>
            </tr>
        </c:forEach>
</c:if>
    </table>
</div>
<div name="order" align="center">
    <form action="/store/cart" method="get" class="decor">
        <div class="form-left-decoration"></div>
        <div class="form-right-decoration"></div>
        <div class="circle"></div>
        <div class="form-inner">
            <h3>Order information ¯\_(ツ)_/¯ </h3><br>
            <input name="delivery" type="text" placeholder="Delivery address" value="${delivery}">
            <input name="email" type="text" placeholder="Email" value="${email}">
            <h4>${error}</h4>
            <form>
                <input type="submit" name="submit" formaction="/store/cart" formmethod="get" value="Pay">
            </form>
            <form>
                <input type="submit" name="submit" formaction="/store" formmethod="get" value="Store">
            </form>
        </div>
    </form>
</div>
</body>
</html>
