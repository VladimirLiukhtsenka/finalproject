<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<%@ include file="include/head.jsp" %>
<body>
<div class="container">
<%@ include file="include/menu.jsp" %>
    <h1 align="center">FESTIVALS</h1>
    <div class="row">
        <div class="col-md-2"></div>
        <div class="col-md-2"><b>Name</b></div>
        <div class="col-md-2"><b>Address</b></div>
        <div class="col-md-2"><b>Description</b></div>
        <div class="col-md-2"><b>Date</b></div>
        <div class="col-md-2"><b></b></div>
    </div>
    <c:forEach items="${events}" var="event">
            <div class="row">
                <div class="col-md-2"></div>
                <div class="col-md-2">${event.name}</div>
                <div class="col-md-2">${event.address}</div>
                <div class="col-md-2">${event.description}</div>
                <div class="col-md-2">${event.date}</div>
                <div class="col-md-2">
                    <form class="form-horizontal"action= "do?command=view_ticket" method="POST">
                        <input id="id" name="id" type="hidden" placeholder="" class="form-control input-md"
                                required="" value="${event.id}">
                        <div class="col-md-3">
                            <button id="view ticket" name="view ticket" class="btn btn-primary">View tickets</button>
                        </div>
                    </form>
                </div>
             </div>
        </c:forEach>
</div>
</body>
</html>