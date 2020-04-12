<%--
  Created by IntelliJ IDEA.
  User: pc
  Date: 11.04.2020
  Time: 13:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login Page</title>
</head>
<body>
    <form action="/" method="post">
        <p>${status}</p>
        <p> Enter name:<input type="text" name="name"></p>
        <p> password:<input type="text" name="password"></p>
        <button type="submit">Log in</button>
    </form>
</body>
</html>
