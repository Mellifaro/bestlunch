<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Restaurants</title>

</head>
<body>
    <h1>Restaurants</h1>
    <table>
        <thead>
        <tr>
            <th>id</th>
            <th>name</th>
            <th>address</th>
            <th>popularity</th>
        </tr>
        </thead>
        <c:forEach items="${restaurants}" var="restaurant">
            <jsp:useBean id="restaurant" scope="page" type="ua.bestlunch.model.Restaurant"/>
                <td>${restaurant.id}</td>
                <td>${restaurant.name}</td>
                <td>${restaurant.address}</td>
                <td>${restaurant.popularity}</td>
                <td><a href="restaurants?action=update&id=${restaurant.id}">Update</a></td>
                <td><a href="restaurants?action=delete&id=${restaurant.id}">Delete</a></td>
            </tr>
        </c:forEach>

    </table>
</body>
</html>
