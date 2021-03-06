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
    <form class="form-horizontal"action = "do?command=sign_up" method="POST">

        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="name"><fmt:message key="message.namePerson"/>*</label>
            <div class="col-md-4">
                <input id="name" name="name" type="text" value="${name}" class="form-control input-md" required="" maxlength=45 pattern="^[a-zA-Z_0-9а-яА-ЯёЁ]{3,45}$">
                 <c:set var = "data" scope = "session" value = "${errorname}"/>
                    <c:if test = "${data != null}">
                     <h5 style="color:#ff0000"><fmt:message key="message.wrongData"/></h5>
                    </c:if>
            </div>
        </div>

        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="surname"><fmt:message key="message.surname"/>*</label>
            <div class="col-md-4">
                <input id="surname" name="surname" type="text" value="${surname}" class="form-control input-md" required="" maxlength=45 pattern="^[a-zA-Z_0-9а-яА-ЯёЁ]{3,45}$">
                 <c:set var = "data" scope = "session" value = "${errorsurname}"/>
                    <c:if test = "${data != null}">
                     <h5 style="color:#ff0000"><fmt:message key="message.wrongData"/></h5>
                    </c:if>
            </div>
        </div>

        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="father name"><fmt:message key="message.fatherName"/>*</label>
            <div class="col-md-4">
                <input id="father_name" name="father_name" type="text" value="${father_name}" class="form-control input-md" required="" maxlength=45 pattern="^[a-zA-Z_0-9а-яА-ЯёЁ]{3,45}$">
                <c:set var = "data" scope = "session" value = "${errorFatherName}"/>
                    <c:if test = "${data != null}">
                     <h5 style="color:#ff0000"><fmt:message key="message.wrongData"/></h5>
                    </c:if>
            </div>
        </div>

        <!-- Select Basic -->
        <div class="form-group">
            <label class="col-md-4 control-label" for="gender"><fmt:message key="message.gender"/>*</label>
            <div class="col-md-4">
                <select id="gender" name="gender" class="form-control">
                    <option value="1" ><fmt:message key="message.gender.male"/></option>
                    <option value="2"><fmt:message key="message.gender.female"/></option>
                </select>
            </div>
        </div>

        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="mail"><fmt:message key="message.mail"/>*</label>
            <div class="col-md-4">
                <input id="mail" name="mail" type="text" value="${mail}" class="form-control input-md" required="" maxlength=30 pattern="^[a-zA-Z_0-9-\+]+(\.[a-zA-Z_0-9-]+)*@[a-zA-Z_0-9-]+(\.[a-zA-Z_0-9]+)*(\.[a-zA-Z]{2,})$">
                <c:set var = "data" scope = "session" value = "${errormail}"/>
                    <c:if test = "${data != null}">
                     <h5 style="color:#ff0000"><fmt:message key="message.wrongData"/></h5>
                    </c:if>
              </div>
        </div>


        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="phone"><fmt:message key="message.phone"/>*</label>
            <div class="col-md-4">
                <input id="phone" name="phone" type="text" value="${phone}" class="form-control input-md" required="" maxlength=13 pattern="^([+])*[\d]{12,13}$">
                 <c:set var = "data" scope = "session" value = "${errorphone}"/>
                    <c:if test = "${data != null}">
                     <h5 style="color:#ff0000"><fmt:message key="message.wrongData"/></h5>
                    </c:if>
                <span class="help-block"><fmt:message key="message.phone.help"/></span>
            </div>
        </div>

        <!-- Password input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="password"><fmt:message key="message.password"/>*</label>
            <div class="col-md-4">
                <input id="password" name="password" type="password" value="${password}" class="form-control input-md" required="" maxlength=45 pattern="^[a-zA-Z_0-9а-яА-ЯёЁ]{8,45}$">
                <c:set var = "data" scope = "session" value = "${errorpassword}"/>
                    <c:if test = "${data != null}">
                     <h5 style="color:#ff0000"><fmt:message key="message.wrongData"/></h5>
                    </c:if>
                <span class="help-block"><fmt:message key="message.password.help"/></span>
            </div>
        </div>

        <!-- Button -->
        <div class="form-group">
            <label class="col-md-4 control-label" for="submitButton"></label>
            <div class="col-md-4">
                <button id="submitButton" name="submitButton" class="btn btn-success"><fmt:message key="button.signUp"/></button>
            </div>
        </div>
    </form>
    <%@ include file="include/footer.jsp" %>
</body>
</html>




