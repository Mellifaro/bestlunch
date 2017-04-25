<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>


<div class="navbar navbar-fixed-top" role="navigation">
    <div class="container">
        <ul class="nav nav-tabs">
            <li role="presentation"><a href="">Home</a></li>
            <li role="presentation"><a href="users/">Users</a></li>
            <li role="presentation"><a href="restaurants/">Restaurants</a></li>
            <li role="presentation"><a href="profile/votes/">My Votes</a></li>
            <li role="presentation"><a href="profile/votes/">About</a></li>
        </ul>

        <ul class="nav navbar-nav navbar-right">
            <li>
                <sec:authorize access="isAnonymous()">
                    <form:form class="navbar-form" role="form" action="spring_security_check"
                               method="post">
                        <div class="form-group">
                            <input type="text" placeholder="Email" class="form-control" name='username'>
                        </div>
                        <div class="form-group">
                            <input type="password" placeholder="Password" class="form-control" name='password'>
                        </div>
                        <button type="submit" class="btn btn-primary">Submit</button>
                        <a class="btn btn-primary" role="button" href="register">Register &raquo;</a>
                        <%--<a href="<c:url value="/j_spring_security_logout" />" >Sign up</a>--%>
                    </form:form>
                </sec:authorize>

                <form:form class="navbar-form" action="logout" method="post">
                    <sec:authorize access="isAuthenticated()">
                        <%--<sec:authentication var ="username" property="principal.username" />--%>
                        <%--receiving username from ModelInterceptor--%>
                        <a class="btn btn-info" role="button" href="profile">${userTo.name} profile</a>
                        <input type="submit" class="btn btn-primary" value="Logout">
                    </sec:authorize>
                </form:form>

                <%--<sec:authorize access="isAuthenticated()">--%>
                    <%--<sec:authentication var ="username" property="principal.username" />--%>
                    <%--<p>Hello, ${username}</p>--%>
                    <%--<a href="<c:url value="/j_spring_security_logout" />">Logout</a>--%>
                <%--</sec:authorize>--%>
            </li>
            <%--<jsp:include page="fragments/lang.jsp"/>--%>
        </ul>

    </div>
</div>

<script>

</script>

