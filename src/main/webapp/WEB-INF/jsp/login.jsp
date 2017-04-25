<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <jsp:include page="fragments/headTag.jsp"/>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
    <link rel="stylesheet" href="webjars/datatables/1.10.12/css/jquery.dataTables.min.css">
</head>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<%--<div class="navbar navbar-fixed-top" role="navigation">--%>
    <%--<div class="container">--%>
        <%--<ul class="nav nav-tabs">--%>
            <%--<li role="presentation"><a href="">Home</a></li>--%>
            <%--<li role="presentation"><a href="about/">About</a></li>--%>
            <%--<div class="navbar-collapse collapse">--%>
                <%--<ul class="nav navbar-nav navbar-right">--%>
                    <%--<li>--%>
                        <%--<form:form class="navbar-form" role="form" action="spring_security_check"--%>
                                   <%--method="post">--%>
                            <%--<div class="form-group">--%>
                                <%--<input type="text" placeholder="Email" class="form-control" name='username'>--%>
                            <%--</div>--%>
                            <%--<div class="form-group">--%>
                                <%--<input type="password" placeholder="Password" class="form-control" name='password'>--%>
                            <%--</div>--%>
                            <%--<button type="submit" class="btn btn-primary">Submit</button>--%>
                        <%--</form:form>--%>
                        <%--&lt;%&ndash;<a href="<c:url value="/logout" />">Sign up</a>&ndash;%&gt;--%>
                    <%--</li>--%>
                    <%--&lt;%&ndash;<jsp:include page="fragments/lang.jsp"/>&ndash;%&gt;--%>
                <%--</ul>--%>

            <%--</div>--%>
            <%--<div class="nav navbar-nav navbar-right">--%>
                <%--<c:if test="${error}">--%>
                    <%--<div class="error">--%>
                            <%--${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}--%>
                    <%--</div>--%>
                <%--</c:if>--%>
                <%--<c:if test="${not empty message}">--%>
                    <%--<div class="message">--%>
                        <%--<spring:message code="${message}"/>--%>
                    <%--</div>--%>
                <%--</c:if>--%>
            <%--</div>--%>
        <%--</ul>--%>
    <%--</div>--%>
<%--</div>--%>

<%--Language : <a href="?language=en">English</a>|<a href="?language=ru">Russian|<a href="?language=ua">Ukrainian</a><br>--%>
<%--Current Locale : ${pageContext.response.locale}--%>
<%--<h1><spring:message code="userlist.name" text="default text"/> </h1>--%>

<div class="jumbotron">
    <div class="container">
        <div class="shadow">
            <h3>Restaurant list</h3>

            <div class="view-box">
                <table class="table table-striped display" id="datatable">
                    <thead>
                    <tr>
                        <th>Name</th>
                        <th>Address</th>
                        <th>Telephone</th>
                        <th>Lunch</th>
                        <th>Dishes</th>
                        <th>Price</th>
                        <th>Votes</th>
                    </tr>
                    </thead>
                </table>

            </div>
        </div>
    </div>
</div>

<jsp:include page="fragments/footer.jsp"/>

</body>
<%--<script type="text/javascript">--%>
<%--<jsp:include page="fragments/i18n.jsp"/>--%>
<%--</script>--%>
<script type="text/javascript" src="webjars/datatables/1.10.12/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="webjars/noty/2.3.8/js/noty/packaged/jquery.noty.packaged.min.js"></script>
<script type="text/javascript" src="webjars/datetimepicker/2.4.7/build/jquery.datetimepicker.full.min.js"></script>
<script type="text/javascript" src="resources/js/loginDatatable.js"></script>
<script type="text/javascript" src="resources/js/navbaar.js"></script>
</html>
