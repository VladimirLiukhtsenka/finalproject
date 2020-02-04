<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<%@ include file="include/head.jsp" %>
<body>
    <div class="container">
        <%@ include file="include/menu.jsp" %>
        <h1 align="center">Ticket offices</h1>
        <div class="row">
            <div class="col-md-3"></div>
            <div class="col-md-3"><b>Address</b></div>
            <div class="col-md-3"><b>Operating mode</b></div>
            <div class="col-md-3"><b>Phone</b></div>
        </div>
        <c:forEach items="${ticketOffices}" var="ticketOffice">
            <div class="row">
                <div class="col-md-3"></div>
                <div class="col-md-3">${ticketOffice.address}</div>
                <div class="col-md-3">${ticketOffice.operatingMode}</div>
                <div class="col-md-3">${ticketOffice.phone}</div>
            </div>
        </c:forEach>
    </div>
    <%@ include file="include/footer.jsp" %>
</body>
</html>