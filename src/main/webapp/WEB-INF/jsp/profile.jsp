<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="bestlunch" tagdir="/WEB-INF/tags" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>

<body>
<jsp:include page="fragments/bodyHeader.jsp"/>

<div class="jumbotron">
    <div class="container">
        <div class="shadow">
            <c:choose>
                <c:when test="${register}">
                    <h3><spring:message code="register.new"/></h3>
                </c:when>
                <c:otherwise>
                    <h3>${userTo.name.concat(' profile')}</h3>
                </c:otherwise>
            </c:choose>

            <div class="view-box">
                <form:form modelAttribute="userTo" class="form-horizontal" method="post"
                           action="${register ? 'register' : 'profile'}" charset="utf-8"
                           accept-charset="UTF-8">

                    <bestlunch:inputField label="Name" name="name"/>
                    <bestlunch:inputField label="Email" name="email"/>
                    <bestlunch:inputField label="Password" name="password" inputType="password"/>

                    <c:choose>
                        <c:when test="${register}">
                            <div class="form-group">
                                <div class="col-xs-offset-2 col-xs-10">
                                    <button type="submit" class="btn btn-primary"><spring:message code="register"/></button>
                                </div>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="form-group">
                                <div class="col-xs-offset-2 col-xs-10">
                                    <button type="submit" class="btn btn-primary"><spring:message code="update"/></button>
                                </div>
                            </div>
                        </c:otherwise>
                    </c:choose>

                </form:form>
            </div>
        </div>
    </div>
</div>

<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
