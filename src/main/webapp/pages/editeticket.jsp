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
    <h1 align="center"><fmt:message key="message.editTicket"/></h1>
    <h5 align="center">${eventName}</h5>
    <form class="form-horizontal"action = "do?command=Create_Ticket" method="POST">
        <!-- Button -->
        <label class="col-md-1 control-label" for="addTicket"></label>
        <div class="form-group">
            <div class="col-md-4">
                <button id="addTicket" name="addTicket" class="btn btn-success"><fmt:message key="button.addTicket"/></button>
            </div>
        </div>
    </form>
    <div class="container">
        <div class="row">
            <div class="col-md-3"><b>Type seat</b></div>
            <div class="col-md-3"><b>Number of tickets</b></div>
            <div class="col-md-3"><b>Price</b></div>
            <div class="col-md-3"><b></b></div>
        </div>
        <c:forEach items="${tickets}" var="ticket">
            <form class="form-horizontal"action= "do?command=delete_ticket" method="POST">
                <div class="row">
                    <div class="col-md-3">${ticket.getTypeSeat().getValue()}</div>
                    <div class="col-md-3">${ticket.numberOfTickets}</div>
                    <div class="col-md-3">${ticket.price}</div>
                    <div class="col-md-3">
                        <form class="form-horizontal">
                            <input id="id" name="id" type="hidden" placeholder="" class="form-control input-md"
                                    required="" value="${ticket.id}">
                            <div class="col-md-3">
                                <button id="delete ticket" name="delete ticket" class="btn btn-danger"><fmt:message key="button.deleteTicket"/></button>
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