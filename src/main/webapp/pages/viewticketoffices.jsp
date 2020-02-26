<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<style>
body {
    background-size: cover;
    background-image: url("images/ticketOfficeBcg.jpg");
}
</style>
<%@ include file="include/head.jsp" %>
<body>
    <div class="container">
        <%@ include file="include/menu.jsp" %>
        <h1 align="center"><fmt:message key="message.ticketOffices"/></h1>
        <div class="row">
            <div class="col-md-3"></div>
            <div class="col-md-3"><b><h4><fmt:message key="field.address"/></h4></b></div>
            <div class="col-md-3"><b><h4><fmt:message key="message.operatingMode"/></h4></b></div>
            <div class="col-md-3"><b><h4><fmt:message key="message.phone"/></h4></b></div>
        </div>
        <c:forEach items="${ticketOffices}" var="ticketOffice">
        <form class="form-horizontal">
            <div class="row">
                <div class="col-md-3"></div>
                <div class="col-md-3"><b>${ticketOffice.address}</b></div>
                <div class="col-md-3"><b>${ticketOffice.operatingMode}</b></div>
                <div class="col-md-3"><b>${ticketOffice.phone}</b></div>
            </div>
              </form>
        </c:forEach>
    </div>
    <%@ include file="include/footer.jsp" %>
</body>
</html>