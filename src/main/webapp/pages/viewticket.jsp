<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<%@ include file="include/head.jsp" %>
<body>
<div class="container">
<%@ include file="include/menu.jsp" %>
    <h1 align="center">View ticket</h1>
    <p align="center">${eventName}</p>
     <div class="container">
        <div class="row">
            <div class="col-md-2"><b>Type seat</b></div>
            <div class="col-md-2"><b>Number of tickets</b></div>
            <div class="col-md-2"><b>Price</b></div>
            <div class="col-md-2"><b>Tickets left</b></div>
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
                                           <button id="buy ticket" name="buy ticket" class="btn btn-success">Buy ticket</button>
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