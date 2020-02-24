<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<%@ include file="include/head.jsp" %>
<body>
<div class="container">
<%@ include file="include/menu.jsp" %>
    <h1 align="center"><fmt:message key="message.viewTicket"/></h1>
    <h3 align="center">${eventName}</h3>
     <div class="container">
        <div class="row">
            <div class="col-md-2"><b><fmt:message key="message.typeSeat"/></b></div>
            <div class="col-md-2"><b><fmt:message key="message.numberOfTickets"/></b></div>
            <div class="col-md-2"><b><fmt:message key="message.price"/></b></div>
            <div class="col-md-2"><b><fmt:message key="message.ticketsLeft"/></b></div>
            <div class="col-md-2"><b></b></div>
        </div>
            <c:forEach var = "i" begin = "0" end = "${end}">
            <form class="form-horizontal"action= "do?command=Buy_ticket" method="POST">
                           <div class="row">
                               <div class="col-md-2">${tickets [i].getTypeSeat().getValue()}</div>
                               <div class="col-md-2">${tickets [i].numberOfTickets}</div>
                               <div class="col-md-2">${tickets [i].price}</div>
                               <div class="col-md-2">${remTickets [i]}</div>
                               <div class="col-md-2">
                                   <form class="form-horizontal">
                                       <input id="ticket_id" name="ticket_id" type="hidden" placeholder="" class="form-control input-md"
                                               required="" value="${tickets [i].id}">
                                       <div class="col-md-2">
                                           <button id="buy ticket" name="buy ticket" class="btn btn-success"><fmt:message key="message.pickUp"/></button>
                                       </div>
                                   </form>
                               </div>
                           </div>
                       </form>
            </c:forEach>
    </div>
</div>
<%@ include file="include/footer.jsp" %>
</body>
</html>