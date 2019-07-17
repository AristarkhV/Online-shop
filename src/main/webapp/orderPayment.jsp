<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Confirm payment</title>
</head>
<body>
<form action="/order/payment" method="post">
    <input type="text" name="confCode" value="${confCode}">
    <input type="hidden" name="email" value="${email}">
    <input type="submit" formaction="/order/payment" formmethod="post" value="Do">
</form>
<h1>${message}</h1>
</body>
</html>
