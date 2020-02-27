<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<style>
body {
    background-size: cover;
    background-image: url("images/profileBack.jpg");
}
</style>
<%@ include file="include/head.jsp" %>
<body>
    <%@ include file="include/menu.jsp" %>
    <h1 align="center"><fmt:message key="message.profile"/></h1>
    <p align="center"><a href=do?command=Update_user><fmt:message key="message.updatePersonData"/></a></p>
    <h3 align="center">${user.name}, <fmt:message key="message.hello"/>!</h3>
    <form class="form-horizontal" action = "do?command=profile" method="POST">
        <!-- Button -->
        <div class="form-group">
            <label class="col-md-1 control-label" for="logout"></label>
            <div class="col-md-1">
                <button id="logout" name="logout" class="btn btn-danger"><fmt:message key="button.logout"/></button>
            </div>
        </div>
    </form>
    <%@ include file="include/footer.jsp" %>
    <c:set var = "size" scope = "session" value = "${TicketsSize}"/>
    <c:if test = "${size > 0}">
    <h3 align="center"><fmt:message key="message.youTickets"/></h3>
    <div class="row">
                <div class="col-md-1"></div>
                <div class="col-md-2"><b><fmt:message key="field.name"/></b></div>
                <div class="col-md-2"><b><fmt:message key="field.address"/></b></div>
                <div class="col-md-2"><b><fmt:message key="field.description"/></b></div>
                <div class="col-md-1"><b><fmt:message key="field.date"/></b></div>
                <div class="col-md-1"><b><fmt:message key="message.typeSeat"/></b></div>
                <div class="col-md-1"><b><fmt:message key="message.price"/></b></div>
                <div class="col-md-2"><b><fmt:message key="message.seatNumber"/></b></div>
            </div>
            <c:forEach items="${userTickets}" var="userTicket">
             <form class="form-horizontal">
                <div class="row">
                    <div class="col-md-1"></div>
                    <div class="col-md-2">${userTicket.get(0)}</div>
                    <div class="col-md-2">${userTicket.get(1)}</div>
                    <div class="col-md-2">${userTicket.get(2)}</div>
                    <div class="col-md-1">${userTicket.get(3)}</div>
                    <div class="col-md-1">${userTicket.get(4)}</div>
                    <div class="col-md-1">${userTicket.get(5)}</div>
                    <div class="col-md-2">${userTicket.get(6)}</div>
                </div>
             </form>
           </c:forEach>
    </c:if>
    </body>
</html>