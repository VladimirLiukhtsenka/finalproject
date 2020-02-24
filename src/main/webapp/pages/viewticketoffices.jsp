<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<%@ include file="include/head.jsp" %>
<body>
    <div class="container">
        <%@ include file="include/menu.jsp" %>
        <h1 align="center"><fmt:message key="message.ticketOffices"/></h1>
        <div class="row">
            <div class="col-md-3"></div>
            <div class="col-md-3"><b><fmt:message key="field.address"/></b></div>
            <div class="col-md-3"><b><fmt:message key="message.operatingMode"/></b></div>
            <div class="col-md-3"><b><fmt:message key="message.phone"/></b></div>
        </div>
        <c:forEach items="${ticketOffices}" var="ticketOffice">
        <form class="form-horizontal">
            <div class="row">
                <div class="col-md-3"></div>
                <div class="col-md-3">${ticketOffice.address}</div>
                <div class="col-md-3">${ticketOffice.operatingMode}</div>
                <div class="col-md-3">${ticketOffice.phone}</div>
            </div>
              </form>
        </c:forEach>
    </div>
    <%@ include file="include/footer.jsp" %>
</body>
</html>