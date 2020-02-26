<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<style>
body {
    background-size: cover;
    background-image: url("images/adminBcg.jpg");
}
</style>
<%@ include file="include/head.jsp" %>
<body>
<div class="container">
<%@ include file="include/menu.jsp" %>
    <h1 align="center"><fmt:message key="message.editEvents"/></h1>
    <form class="form-horizontal" action = "do?command=create_event" method="POST">
        <!-- Button -->
        <label class="col-md-1 control-label" for="addEvent"></label>
        <div class="form-group">
            <div class="col-md-4">
                <button id="addEvent" name="addEvent" class="btn btn-success"><fmt:message key="button.addEvent"/></button>
            </div>
        </div>
    </form>

    <div class="container">
        <div class="row">
            <div class="col-md-2"><b><fmt:message key="field.name"/></b></div>
            <div class="col-md-2"><b><fmt:message key="field.address"/></b></div>
            <div class="col-md-2"><b><fmt:message key="field.description"/></b></div>
            <div class="col-md-2"><b><fmt:message key="field.date"/></b></div>
            <div class="col-md-1"><b><fmt:message key="field.typeEvent"/></b></div>
            <div class="col-md-3"><b></b></div>
        </div>
        <c:forEach items="${events}" var="event">
            <form class="form-horizontal" action = "do?command=Edit_ticket" method = "POST">
                <div class="row" style ="height: 100px;">
                    <div class="col-md-2">${event.name}</div>
                    <div class="col-md-2">${event.address}</div>
                    <div class="col-md-2">${event.description}</div>
                    <div class="col-md-2">${event.date}</div>
                    <div class="col-md-1">${event.getTypeOfEvent().getValue()}</div>
                    <div class="col-md-3">
                        <form class="form-horizontal" action = "do?command=Edit_ticket" method = "POST">
                            <input id="id" name="id" type="hidden" placeholder="" class="form-control input-md"
                                                                required="" value="${event.id}">
                            <div class="col-md-5">
                                <button id="Edit_ticket" name="Edit_ticket" class="btn btn-primary"><fmt:message key="message.editTicket"/></button>
                            </div>
                        </form>
                        <form class="form-horizontal-${event.id}" action="do?command=delete_event" method="POST">
                            <input id="id" name="id" type="hidden" placeholder="" class="form-control input-md"
                                            required="" value="${event.id}">
                            <div class="col-md-5">
                                <button id="delete event" name="delete event" class="btn btn-danger"><fmt:message key="button.deleteEvent"/></button>
                            </div>
                        </form>
                    </div>
                </div>
            </form>
        </c:forEach>
    </div>
</div>
     <table style = "margin: auto;padding: 0;">
            <tr>
                <c:if test="${currentPage != 1}">
                    <td><a href="do?command=Edit_event&page=${currentPage - 1}"><fmt:message key="button.previous"/></a></td>
                </c:if>
                <c:forEach begin="1" end="${noOfPages}" var="i">
                    <c:choose>
                        <c:when test="${currentPage == i}">
                            <td style = "padding: 6px;">${i}</td>
                        </c:when>
                        <c:otherwise>
                            <td style = "padding: 6px;"><a href="do?command=Edit_event&page=${i}">${i}</a></td>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                <c:if test="${currentPage < noOfPages}">
                    <td><a href="do?command=Edit_event&page=${currentPage + 1}"><fmt:message key="button.next"/></a></td>
                </c:if>
            </tr>
    </table>
</body>
</html>