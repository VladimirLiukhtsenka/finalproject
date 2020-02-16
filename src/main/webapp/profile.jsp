<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<%@ include file="include/head.jsp" %>
<body>
    <%@ include file="include/menu.jsp" %>
    <h1 align="center">Profile</h1>
    <p align="center"><a href=do?command=Update_user>Update personal data</a></p>
    <h2>${user.name},Hello! </h2>
    <form class="form-horizontal" action = "do?command=profile" method="POST">
        <!-- Button -->
        <div class="form-group">
            <label class="col-md-0 control-label" for="logout"></label>
            <div class="col-md-0">
                <button id="logout" name="logout" class="btn btn-danger">Logout</button>
            </div>
        </div>
    </form>
    <%@ include file="include/footer.jsp" %>
    <h2 align="center">Your tickets</h2>
    <div class="row">
                <div class="col-md-2"><b>Name</b></div>
                <div class="col-md-2"><b>Address</b></div>
                <div class="col-md-2"><b>Description</b></div>
                <div class="col-md-2"><b>Date</b></div>
                <div class="col-md-1"><b>Type seat</b></div>
                <div class="col-md-1"><b>Price</b></div>
                <div class="col-md-1"><b>Seat number</b></div>
            </div>
            <c:forEach items="${userTickets}" var="userTicket">
             <form class="form-horizontal">
                <div class="row">
                    <div class="col-md-2">${userTicket.get(0)}</div>
                    <div class="col-md-2">${userTicket.get(1)}</div>
                    <div class="col-md-2">${userTicket.get(2)}</div>
                    <div class="col-md-2">${userTicket.get(3)}</div>
                    <div class="col-md-1">${userTicket.get(4)}</div>
                    <div class="col-md-1">${userTicket.get(5)}</div>
                    <div class="col-md-1">${userTicket.get(6)}</div>
                </div>
             </form>
           </c:forEach>
    </body>
</html>