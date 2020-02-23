<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<%@ include file="include/head.jsp" %>
<body>
    <%@ include file="include/menu.jsp" %>
    <form class="form-horizontal"action = "do?command=login" method="POST">
        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="mail">Mail*</label>
            <div class="col-md-4">
                <input id="mail" name="mail" type="text" placeholder="" class="form-control input-md" required="" maxlength=30 pattern="^[\w-\+]+(\.[\w-]+)*@[\w-]+(\.[\w]+)*(\.[a-zA-Z]{2,})$">
            </div>
        </div>
        <!-- Password input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="password">Password*</label>
            <div class="col-md-4">
                <input id="password" name="password" type="password" placeholder="" class="form-control input-md" required="" maxlength=45 pattern="^[\wа-яА-ЯёЁ]{8,45}$" >
                <span class="help-block">no less than 8 characters</span>
            </div>
        </div>
        <!-- Button -->
        <div class="form-group">
            <label class="col-md-4 control-label" for="LoginButton"></label>
            <div class="col-md-4">
                <button id="LoginButton" name="LoginButton" class="btn btn-success">Login</button>
            </div>
        </div>
    </form>
    <%@ include file="include/footer.jsp" %>
</body>
</html>

