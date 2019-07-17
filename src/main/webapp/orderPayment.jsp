<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Confirm payment</title>
</head>
<body>
    <input type="text" name="confCode">
<form>
    <input type="submit" formaction="/order/payment" formmethod="get" value="Do">
</form>
<h1>${message}</h1>
</body>
</html>
