<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style>
    <%@include file='css/errorsStyle.css' %>
</style>
<html>
<head>
    <title>Confirm payment</title>
</head>
<body>
<div class="base">
<form action="/order/payment" method="post">
    <input type="text" name="confCode" value="${confCode}">
    <input type="hidden" name="email" value="${email}">
    <input type="submit" formaction="/order/payment" formmethod="post" value="Confirm">
</form>
<h1>${message}</h1>
</div>
</body>
</html>
