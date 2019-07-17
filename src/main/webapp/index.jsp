<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
    <%@include file='css/index.css'%>
</style>
<html>
<head>
    <title>Hello</title>
</head>
<body>
<form action="login" method="post" class="decor">
    <div class="form-left-decoration"></div>
    <div class="form-right-decoration"></div>
    <div class="circle"></div>
    <div class="form-inner">
        <h3>Hello ¯\_(ツ)_/¯ </h3><br>
        <input name="email" type="email" placeholder="Email" value="${email}">
        <input name="password" type="password" placeholder="Password">
        <h4>${error}</h4>
        <form>
            <input type="submit" name="submit" formaction="/login" formmethod="post" value="Sign in">
        </form>
        <a href="addUser.jsp">Registration</a><br>
    </div>
</form>

</body>
</html>
