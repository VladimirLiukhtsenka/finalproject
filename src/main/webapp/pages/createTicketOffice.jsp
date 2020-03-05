<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<style>
body {
    background-size: cover;
    background-image: url("images/adminBcg.jpg");
}
</style>
<%@ include file="include/head.jsp" %>
<body>
<div class="container">
<%@ include file="include/menu.jsp" %>
<h1 align="center"><fmt:message key="button.createTicketOffice"/></h1>
    <form class="form-horizontal"action = "do?command=create_ticket_office" method="POST" >

        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="address"><fmt:message key="field.address"/>*</label>
            <div class="col-md-4">
                <input id="address" name="address" type="text" placeholder="" class="form-control input-md" required="" maxlength=45 pattern="^[\\p a-zA-Z_0-9а-яА-ЯёЁ]{3,45}$">
            </div>
        </div>

        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="description"><fmt:message key="field.operatingMode"/>*</label>
            <div class="col-md-4">
                <input id="operating_mode" name="operating_mode" type="text" placeholder="" class="form-control input-md" required="" maxlength=30 pattern="^[\\p a-zA-Z_0-9а-яА-ЯёЁ]{3,45}$">
            </div>
        </div>

        <!-- Text input-->
           <div class="form-group">
               <label class="col-md-4 control-label" for="phone"><fmt:message key="message.phone"/>*</label>
               <div class="col-md-4">
                   <input id="phone" name="phone" type="text" class="form-control input-md" required="" maxlength=13 pattern="^([+])*[\d]{12,13}$">
                   <span class="help-block"><fmt:message key="message.phone.help"/></span>
               </div>
           </div>

        <!-- Button -->
        <div class="form-group" >
            <label class="col-md-4 control-label" for="create"></label>
            <div class="col-md-4">
                <button id="create" name="create" class="btn btn-success"><fmt:message key="button.createTicketOffice"/></button>
            </div>
        </div>
    </form>
</div>
    <c:set var = "data" scope = "session" value = "${wrongData}"/>
        <c:if test = "${data != null}">
        <h5 align="center"><fmt:message key="message.wrongData"/></h5>
    </c:if>
</body>
</html>