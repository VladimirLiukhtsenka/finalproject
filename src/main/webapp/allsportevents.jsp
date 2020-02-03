<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
       <html>
       <%@ include file="include/menu.htm" %>
       <body>
<h1 align="center">SPORT</h1>
    <div class="row">
        <div class="col-md-2"></div>
        <div class="col-md-2"><b>Name</b></div>
        <div class="col-md-2"><b>Address</b></div>
        <div class="col-md-2"><b>Description</b></div>
        <div class="col-md-2"><b>Date</b></div>
    </div>

    <c:forEach items="${events}" var="event">
        <div class="row">
            <div class="col-md-2"></div>
            <div class="col-md-2">${event.name}</div>
            <div class="col-md-2">${event.address}</div>
            <div class="col-md-2">${event.description}</div>
            <div class="col-md-2">${event.date}</div>
         </div>
    </c:forEach>

<%@ include file="include/head.htm" %>
</body>
</html>