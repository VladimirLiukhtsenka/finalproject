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
    <h1 align="center"><fmt:message key="message.editTicketOffice"/></h1>
    <form class="form-horizontal" action = "do?command=create_ticket_office" method="POST">
        <!-- Button -->
        <label class="col-md-1 control-label" for="addEvent"></label>
        <div class="form-group">
            <div class="col-md-1">
                <button id="addTicketOffice" name="addTicketOffice" class="btn btn-success"><fmt:message key="button.addTicketOffice"/></button>
            </div>
        </div>
    </form>

    <div class="container">
        <div class="row">
            <div class="col-md-3"><h4><fmt:message key="field.address"/></h4></div>
            <div class="col-md-3"><h4><fmt:message key="message.operatingMode"/></h4></div>
            <div class="col-md-3"><h4><fmt:message key="message.phone"/></h4></div>
            <div class="col-md-3"></div>
        </div>
        <c:forEach items="${ticketOffices}" var="ticketOffice">
            <form class="form-horizontal" action = "do?command=delete_ticket_office" method = "POST">
                <div class="row">
                    <div class="col-md-3">${ticketOffice.address}</div>
                    <div class="col-md-3">${ticketOffice.operatingMode}</div>
                    <div class="col-md-3">${ticketOffice.phone}</div>
                    <div class="col-md-3">
                        <form class="form-horizontal-${ticketOffice.phone}" action="do?command=delete_ticket_office" method="POST">
                            <input id="phone" name="phone" type="hidden" placeholder="" class="form-control input-md"
                                            required="" value="${ticketOffice.phone}">
                            <div class="col-md-3">
                                <button id="delete ticket office" name="delete ticket office" class="btn btn-danger"><fmt:message key="button.deleteTicketOffice"/></button>
                            </div>
                        </form>
                    </div>
                </div>
            </form>
        </c:forEach>
    </div>
</div>
</body>
</html>