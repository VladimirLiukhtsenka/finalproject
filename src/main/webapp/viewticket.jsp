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
            <div class="col-md-3"><b>Type seat</b></div>
            <div class="col-md-3"><b>Number of tickets</b></div>
            <div class="col-md-3"><b>Price</b></div>
            <div class="col-md-3"><b></b></div>
        </div>
            <c:forEach var = "i" begin = "0" end = "2">
            <form class="form-horizontal"action= "do?command=Buy_ticket" method="POST">
                           <div class="row">
                               <div class="col-md-3">${tickets [i].getTypeSeat().getValue()}</div>
                               <div class="col-md-3">${tickets [i].numberOfTickets}</div>
                               <div class="col-md-3">${tickets [i].price}</div>
                               <div class="col-md-3">${remTickets [i]}</div>
                               <div class="col-md-3">
                                   <form class="form-horizontal">
                                       <input id="id" name="id" type="hidden" placeholder="" class="form-control input-md"
                                               required="" value="${ticket.id}">
                                       <div class="col-md-3">
                                           <button id="buy ticket" name="buy ticket" class="btn btn-success">Buy ticket</button>
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