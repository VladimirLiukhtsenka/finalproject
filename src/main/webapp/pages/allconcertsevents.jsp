<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<style>
body {
    background-size: cover;
    background-image: url("images/boardbright.jpg");
}
</style>
<%@ include file="include/head.jsp" %>
<body>
<div class="container">
<%@ include file="include/menu.jsp" %>
    <h1 align="center">CONCERTS</h1>
    <div class="row">
        <div class="col-md-2"></div>
        <div class="col-md-2"><b>Name</b></div>
        <div class="col-md-2"><b>Address</b></div>
        <div class="col-md-2"><b>Description</b></div>
        <div class="col-md-2"><b>Date</b></div>
        <div class="col-md-2"><b></b></div>
    </div>
    <c:forEach items="${events}" var="event">
     <form class="form-horizontal"action= "do?command=view_ticket" method="POST">
        <div class="row" style ="height: 100px;">
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
     </form>
    </c:forEach>
</div>
     <table style = "margin: auto;padding: 0;">
            <tr>
                <c:if test="${currentPage != 1}">
                    <td><a href="do?command=View_concerts_event&page=${currentPage - 1}">Previous</a></td>
                </c:if>
                <c:forEach begin="1" end="${noOfPages}" var="i">
                    <c:choose>
                        <c:when test="${currentPage == i}">
                            <td style = "padding: 6px;">${i}</td>
                        </c:when>
                        <c:otherwise>
                            <td style = "padding: 6px;"><a href="do?command=View_concerts_event&page=${i}">${i}</a></td>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                <c:if test="${currentPage < noOfPages}">
                    <td><a href="do?command=View_concerts_event&page=${currentPage + 1}">Next</a></td>
                </c:if>
            </tr>
    </table>
</body>
</html>