<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="navbar navbar-default navbar-fixed-top">
    <div class="container">

        <center>
            <div class="navbar-collapse collapse" id="navbar-main">
                <ul class="nav navbar-nav">
                    <li class="active"><a href=""><spring:message code="app.navbar.home"/></a>
                    </li>
                    <li><a href="users/"><spring:message code="app.navbar.users"/></a>
                    </li>
                    <li><a href="restaurants/"><spring:message code="app.navbar.restaurants"/></a>
                    </li>
                    <li><a href="votes/"><spring:message code="app.navbar.myvotes"/></a>
                    </li>
                    <li><a href="profile/votes/"><spring:message code="app.navbar.about"/></a>
                    </li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">${pageContext.response.locale}<b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li><a onclick="show('en')">English</a></li>
                            <li><a onclick="show('ru')">Русский</a></li>
                            <li><a onclick="show('ua')">Українська</a></li>
                        </ul>
                    </li>
                </ul>
                <sec:authorize access="isAnonymous()">
                    <form:form class="navbar-form navbar-right" role="form" action="spring_security_check"
                               method="post">
                        <div class="form-group">
                            <input type="text" placeholder="<spring:message code="email"/>" class="form-control" name='username'>
                        </div>
                        <div class="form-group">
                            <input type="password" placeholder="<spring:message code="password"/>" class="form-control" name='password'>
                        </div>
                        <button type="submit" class="btn btn-default"><spring:message code="signin"/></button>
                        <a class="btn btn-default" role="button" href="register"><spring:message code="register"/> &raquo;</a>
                        <%--<a href="<c:url value="/j_spring_security_logout" />" >Sign up</a>--%>
                    </form:form>
                </sec:authorize>

                <form:form class="navbar-form navbar-right" action="logout" method="post">
                    <sec:authorize access="isAuthenticated()">
                        <%--<sec:authentication var ="username" property="principal.username" />--%>
                        <%--receiving username from ModelInterceptor--%>
                        <a class="btn btn-default" role="button" href="profile">${userTo.name} <spring:message code="profile"/></a>
                        <input type="submit" class="btn btn-default" value=<spring:message code="logout"/>>
                    </sec:authorize>
                </form:form>

            </div>
        </center>
    </div>
</div>

<script type="text/javascript">
    function show(lang) {
        window.location.href = window.location.href.split('?')[0] + '?lang=' + lang;
    }
</script>

