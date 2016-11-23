<!DOCTYPE html>
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
<head>
    <title>Users</title>

    <style type="text/css">
        .tg {
            border-collapse: collapse;
            border-spacing: 0;
            border-color: #ccc;
        }

        .tg td {
            font-family: Arial, sans-serif;
            font-size: 16px;
            padding: 10px 5px;
            border-style: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
            border-color: #ccc;
            color: #333;
            background-color: #fff;
        }

        .tg th {
            font-family: Arial, sans-serif;
            font-size: 16px;
            font-weight: normal;
            padding: 10px 5px;
            border-style: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
            border-color: #ccc;
            color: #333;
            background-color: #f0f0f0;
        }

        .tg .tg-4eph {
            background-color: #f9f9f9
        }
        .text {
            font-family: "Times New Roman", sans-serif;
            font-size: 30px;
            text-align:  center;
        }
        .text1 {
            font-family: "Times New Roman", sans-serif;
            font-size: 30px;
            text-align:  left;
        }
        .text2 {
            font-family: "Times New Roman", sans-serif;
            font-size: 14px;
            text-align:  center;
        }
    </style>

</head>
<body>
Language : <a href="?language=en">English</a>|<a href="?language=ru">Russian|<a href="?language=ua">Ukrainian</a><br>
Current Locale : ${pageContext.response.locale}
<h1><spring:message code="userlist.name" text="default text"/> </h1>
<table table class="table table-striped table-bordered">
    <tr class="success">
        <th width="80">id</th>
        <th width="80">name</th>
        <th width="80">email</th>
        <th width="80">password</th>
        <th width="80">registered</th>
        <th width="80">roles</th>
        <th width="80">edit</th>
        <th width="80">delete</th>
    </tr>
    </thead>
    <c:forEach items="${users}" var="user">
        <jsp:useBean id="user" scope="page" type="ua.bestlunch.model.User"/>
        <td>${user.id}</td>
        <td>${user.name}</td>
        <td>${user.email}</td>
        <td>${user.password}</td>
        <td>${user.registered}</td>
        <td>${user.roles}</td>
        <td><a href="restaurants?action=update&id=${restaurant.id}">Update</a></td>
        <td><a href="restaurants?action=delete&id=${restaurant.id}">Delete</a></td>
        </tr>
    </c:forEach>
</table>
<button type="button" class="btn btn-primary">Add user</button>


</body>
</html>
