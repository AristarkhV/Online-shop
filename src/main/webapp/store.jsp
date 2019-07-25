<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
    <%@include file='css/style.css'%>
</style>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Store</title>
</head>
<header>
    <h1 align="center">Strange store ¯\_(ツ)_/¯</h1>
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
<div class="error">
    <h3>${message}</h3>
</div>
<div class="products">
    <c:forEach var="product" items="${products}">
        <div class="productDiv">
            <h2>${product.name}</h2>
            <h4>${product.description}</h4>
            <h3>${product.price} $</h3>
            <form action="/store/add/cart" method="post" class="cart-btn">
                <input type="hidden" name="productID" value="${product.productID}">
                <button class="add" type="submit">Buy</button>
            </form>
        </div>
    </c:forEach>
</div>
</body>
</html>
