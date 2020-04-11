<%@ page import="java.util.Date" %><%--
  Created by IntelliJ IDEA.
  User: pc
  Date: 11.04.2020
  Time: 13:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users Main Page</title>
</head>
<body>
    <p>Date login: <%= new Date().toString() %></p>
    <form action="/login" method="get">
        <a href="/login"></a>
        <button type="submit">LogOut</button>
    </form>
</body>
</html>
