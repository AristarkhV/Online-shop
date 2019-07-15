<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
    <%@include file='css/style.css' %>
</style>
<html>
<head>
    <title>Registration</title>
</head>

<body>
<form action="/admin/add/user" method="post" class="decor">
    <div class="form-left-decoration"></div>
    <div class="form-right-decoration"></div>
    <div class="circle"></div>
    <div class="form-inner">
        <h3>Registration</h3><br>
        <input name="email" type="email" placeholder="Email" value="${email}">
        <input name="password" type="password" placeholder="Password" value="${userPassword}">
        <input name="rpassword" type="password" placeholder="Repeat password">
        <input type="radio" name="role" value="admin">Admin
        <input type="radio" name="role" value="user" checked>User
        <input type="hidden" name="userId" value="${userId}">
        <h4>${error}</h4>
        <h4>${done}</h4>
        <input name="submit" type="submit" value="Do">
        <form>
            <input type="submit" formaction="/admin/users" formmethod="post" value="Users page">
        </form>
    </div>
</form>
</body>
