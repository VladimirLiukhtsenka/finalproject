<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<%@ include file="include/menu.htm" %>
<body>
<h1 align="center">Edit events</h1>
<form class="form-horizontal"action = "do?command=CreateEvent" method="POST">
    <!-- Button -->
    <label class="col-md-1 control-label" for="addEvent"></label>
    <div class="form-group">
        <div class="col-md-4">
            <button id="add Event" name="addEvent" class="btn btn-success">Add event</button>
        </div>
    </div>
</form>

<div class="container">
    <div class="row">
        <div class="col-md-2"><b>Name</b></div>
        <div class="col-md-2"><b>Address</b></div>
        <div class="col-md-2"><b>Description</b></div>
        <div class="col-md-2"><b>Date</b></div>
        <div class="col-md-2"><b>Type</b></div>
        <div class="col-md-2"><b></b></div>
    </div>
    <c:forEach items="${events}" var="event">
    <form class="form-horizontal-${event.id}" action="do?command=delete_event" method="post">
            <div class="row">
            <input id="id" name="id" type="hidden" placeholder="" class="form-control input-md"
                                   required="" value="${event.id}">
                <div class="col-md-2">${event.name}</div>
                <div class="col-md-2">${event.address}</div>
                <div class="col-md-2">${event.description}</div>
                <div class="col-md-2">${event.date}</div>
                <div class="col-md-2">${event.getTypeOfEvent().getValue()}</div>
                <!-- Button -->
                <div class="col-md-2">
                    <button id="delete event" name="delete event" class="btn btn-danger">Delete event</button>
                </div>
             </div>
         </form>
    </c:forEach>
</div>

<%@ include file="include/head.htm" %>
</body>
</html>