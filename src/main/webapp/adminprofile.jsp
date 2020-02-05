<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
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
                <a class="navbar-brand" href="do?command=home">Home</a>
            </div>
                <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li><a href=do?command=Edit_event>edit-events</a></li>
                </ul>
                    <ul class="nav navbar-nav navbar-right">
                </ul>
            </div>
        </div>
    </nav>
    <h1 align="center">Admin page</h1>
    <p>Hello! ${user.name}</p>
    <form class="form-horizontal" action = "do?command=admin_profile" method="POST">
        <!-- Button -->
        <div class="form-group">
            <label class="col-md-2 control-label" for="logout">Single Button</label>
            <div class="col-md-2">
                <button id="logout" name="logout" class="btn btn-danger">Logout</button>
            </div>
        </div>
    </form>
    <%@ include file="include/footer.jsp" %>
</body>
</html>