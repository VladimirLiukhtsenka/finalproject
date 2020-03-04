<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<style>
body {
    background-size: cover;
    background-image: url("images/adminBcg.jpg");
}
</style>
<%@ include file="include/head.jsp" %>
<body>
     <nav class="navbar navbar-default">
        <div class="container-fluid">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="do?command=home"><fmt:message key="navBar.home"/></a>
            </div>
                <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li><a href=do?command=Edit_event><fmt:message key="navBar.editEvents"/></a></li>
                    <li><a href=do?command=Edit_ticket_office><fmt:message key="navBar.editTicketOffice"/></a></li>
                </ul>
                    <ul class="nav navbar-nav navbar-right">
                </ul>
            </div>
        </div>
    </nav>
    <h1 align="center"><fmt:message key="message.adminPage"/></h1>
    <h3 align="center">${user.name}, <fmt:message key="message.hello"/>!</h3>
    <form class="form-horizontal" action = "do?command=admin_profile" method="POST">
        <!-- Button -->
        <div class="form-group">
            <label class="col-md-1 control-label" for="logout"></label>
            <div class="col-md-1">
                <button id="logout" name="logout" class="btn btn-danger"><fmt:message key="button.logout"/></button>
            </div>
        </div>
    </form>
    <%@ include file="include/footer.jsp" %>
</body>
</html>