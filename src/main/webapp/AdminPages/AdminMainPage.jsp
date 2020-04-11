<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="services.UserService" %>
<%@ page import="java.util.List" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="entities.User" %><%--
  Created by IntelliJ IDEA.
  User: pc
  Date: 09.04.2020
  Time: 11:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<title>Main_Page</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">

<body>
    <div class="w3-container">
         <h2>List Users</h2>
    <table class="w3-table w3-striped">
         <tr>
              <th>Id</th>
              <th>Name</th>
              <th>Password</th>
              <th>Role</th>
              <th></th>
              <th></th>
         </tr>

      <c:forEach var="usersList" items="${users}" >

        <tr>
            <td>${usersList.id}</td>
            <td>${usersList.name}</td>
            <td>${usersList.password}</td>
            <td>${usersList.role}</td>
            <td>
                <form method="get" action="/admin/editUser">
                    <div>
                        <button type="submit">
                            <input hidden="true" value=${usersList.id} name="id"> <!--Передаю параметры в editUserPage.jsp-->
                            <input hidden="true" value=${usersList.name} name="name">
                            <input hidden="true" value=${usersList.password} name="password">
                            <img src="https://upload.wikimedia.org/wikipedia/commons/c/cc/Edit_Notepad_Icon.svg"
                                 width="20px" height="20px">
                        </button>
                    </div>
                </form>
            </td>
            <td>
                <form method="get" action="/admin/deleteUser">
                    <div>
                        <button type="submit">
                            <img src="https://cdn1.iconfinder.com/data/icons/ios-and-android-line-set-2/52/delete__remove__trash__dustbin-512.png"
                            style="vertical-align: middle"
                            width="20px" height="20px">
                            <input hidden="true" value=${usersList.id} name="id">
                        </button>
                    </div>
                </form>
            </td>
        </tr>
        </c:forEach>

    </table>
    </div>

    <style>
        .w3-button {width:100px;}
    </style>

        <form method="get" action="/admin/addUser">
            <div class="w3-container">
                <button class="w3-button w3-cyan" type="submit" style="width: 150px">New</button>
            </div>
        </form>

</body>
</html>
