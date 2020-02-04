<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<%@ include file="include/head.jsp" %>
<body>
    <%@ include file="include/menu.jsp" %>
    <p>Hello! ${user.name}</p>
    <form class="form-horizontal" action = "do?command=profile" method="POST">
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