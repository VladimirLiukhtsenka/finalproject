<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container">
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="do?command=home"><fmt:message key="navBar.home"/></a>
        </div>
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li><a href=do?command=View_sport_event><fmt:message key="navBar.sport"/></a></li>
                <li><a href=do?command=View_concerts_event><fmt:message key="navBar.concerts"/></a></li>
                <li><a href=do?command=View_festivals_event><fmt:message key="navBar.festivals"/></a></li>
                <li><a href=do?command=View_theater_event><fmt:message key="navBar.theater"/></a></li>
                <li><a href=do?command=View_for_children_event><fmt:message key="navBar.forChildren"/></a></li>
                <li><a href=do?command=View_for_movie_event><fmt:message key="navBar.movie"/></a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href=do?command=View_ticket_office><fmt:message key="navBar.ticketOffice"/></a></li>
                <c:set var = "guest" scope = "session" value = "${user}"/>
                    <c:if test = "${guest == null}">
                        <li><a href=do?command=Login><fmt:message key="navBar.login"/></a></li>
                        <li><a href=do?command=Sign_Up><fmt:message key="navBar.signUp"/></a></li>
                    </c:if>
                <c:set var = "isUser" scope = "session" value = "${isUser}"/>
                <c:if test = "${isUser == true}">
                    <li><a href=do?command=Profile><fmt:message key="navBar.profile"/></a></li>
                </c:if>
                <c:set var = "isAdmin" scope = "session" value = "${isAdmin}"/>
                <c:if test = "${isAdmin == true}">
                    <li><a href=do?command=Admin_profile><fmt:message key="navBar.adminProfile"/></a></li>
                </c:if>
                </ul>
         </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>
</div>