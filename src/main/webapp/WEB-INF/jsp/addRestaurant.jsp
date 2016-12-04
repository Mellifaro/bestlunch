<%--
  Created by IntelliJ IDEA.
  User: Виктор
  Date: 27.11.2016
  Time: 1:40
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <title>Add Restaurant</title>
    <link rel="stylesheet" href="webjars/bootstrap/3.3.6/css/bootstrap.min.css">
</head>
<body>
    <h1>Add Restaurant</h1>

    <form method="post" action="/bestlunch/addrestaurant">
        <div class="form-group" >
            <label for="name">Name:</label>
            <input type="text" class="form-control" id="name" name="name">
        </div>
        <div class="form-group">
            <label for="address">Address:</label>
            <input type="text" class="form-control" id="address" name="address">
        </div>
        <div class="form-group">
            <label for="telephone">Telephone:</label>
            <input type="tel" class="form-control" id="telephone" name="telephone">
        </div>
        <button type="submit" class="btn btn-default">Submit</button>
    </form>
</body>
</html>
