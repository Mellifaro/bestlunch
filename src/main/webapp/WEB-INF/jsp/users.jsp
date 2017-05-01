<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
    <jsp:include page="fragments/headTag.jsp"/>
    <link rel="stylesheet" href="webjars/datatables/1.10.12/css/jquery.dataTables.min.css">
<body>
    <jsp:include page="fragments/bodyHeader.jsp"/>


<%--Language : <a href="?language=en">English</a>|<a href="?language=ru">Russian|<a href="?language=ua">Ukrainian</a><br>--%>
<%--Current Locale : ${pageContext.response.locale}--%>
<%--<h1><spring:message code="userlist.name" text="default text"/> </h1>--%>

<div class="jumbotron">
    <div class="container">
        <div class="shadow">
            <h3><spring:message code="userlist"/></h3>

            <div class="view-box">
                <a class="btn btn-sm btn-info" onclick="add('<spring:message code="users.add"/>')" ><spring:message code="users.add"/></a>

                <table class="table table-striped display" id="datatable">
                    <thead>
                    <tr>
                        <th><spring:message code="table.name"/></th>
                        <th><spring:message code="email"/></th>
                        <th><spring:message code="table.roles"/></th>
                        <th><spring:message code="password"/></th>
                        <th><spring:message code="table.enabled"/></th>
                        <th><spring:message code="table.registered"/></th>
                        <th></th>
                        <th></th>
                    </tr>
                    </thead>
                </table>

            </div>
        </div>
    </div>
</div>

<jsp:include page="fragments/footer.jsp"/>

<div class="modal fade" id="editRow">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h2 class="modal-title" id="modalTitle"></h2>
            </div>
            <div class="modal-body">
                <form:form class="form-horizontal" method="post" id="detailsForm">
                    <input type="text" hidden="hidden" id="id" name="id">

                    <div class="form-group">
                        <label for="name" class="control-label col-xs-3"><spring:message code="name"/></label>

                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="name" name="name" placeholder="Name">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="email" class="control-label col-xs-3"><spring:message code="email"/></label>

                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="email" name="email" placeholder="Email">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="password" class="control-label col-xs-3"><spring:message code="password"/></label>

                        <div class="col-xs-9">
                            <input type="password" class="form-control" id="password" name="password" placeholder="Email">
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-xs-offset-3 col-xs-9">
                            <button type="submit" class="btn btn-primary"><spring:message code="save"/></button>
                        </div>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>


</body>
    <%--<script type="text/javascript">--%>
        <%--<jsp:include page="fragments/i18n.jsp"/>--%>
    <%--</script>--%>
    <script type="text/javascript" src="webjars/datatables/1.10.12/js/jquery.dataTables.min.js"></script>
    <script type="text/javascript" src="webjars/noty/2.3.8/js/noty/packaged/jquery.noty.packaged.min.js"></script>
    <script type="text/javascript" src="webjars/datetimepicker/2.4.7/build/jquery.datetimepicker.full.min.js"></script>
    <script type="text/javascript" src="resources/js/datatablesUtil.js"></script>
    <script type="text/javascript" src="resources/js/userDatatable.js"></script>
</html>
