<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<style>
body {
    background-size: cover;
    background-image: url("images/loginBackground.jpg");
}
</style>
<%@ include file="include/head.jsp" %>
<body>
    <%@ include file="include/menu.jsp" %>
    <form class="form-horizontal"action = "do?command=login" method="POST">
        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="mail"><fmt:message key="message.mail"/>*</label>
            <div class="col-md-4">
                <input id="mail" name="mail" type="text" placeholder="" class="form-control input-md" required="" maxlength=30
                pattern="^[a-zA-Z_0-9-\+]+(\.[a-zA-Z_0-9-]+)*@[a-zA-Z_0-9-]+(\.[a-zA-Z_0-9]+)*(\.[a-zA-Z]{2,})$">
            </div>
        </div>
        <!-- Password input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="password"><fmt:message key="message.password"/>*</label>
            <div class="col-md-4">
                <input id="password" name="password" type="password" placeholder="" class="form-control input-md" required="" maxlength=45 pattern="^[a-zA-Z_0-9а-яА-ЯёЁ]{8,45}$" >
                <span class="help-block">no less than 8 characters</span>
            </div>
        </div>
        <!-- Button -->
        <div class="form-group">
            <label class="col-md-4 control-label" for="LoginButton"></label>
            <div class="col-md-4">
                <button id="LoginButton" name="LoginButton" class="btn btn-success"><fmt:message key="button.login"/></button>
            </div>
        </div>
    </form>
    <c:set var = "data" scope = "session" value = "${wrongData}"/>
    <c:if test = "${data != null}">
    <h5 align="center"><fmt:message key="message.wrongData"/></h5>
    </c:if>
    <%@ include file="include/footer.jsp" %>
</body>
</html>

