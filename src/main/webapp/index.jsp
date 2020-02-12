<%@ taglib prefix="ctg" uri="/WEB-INF/tld/custom.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<%@ include file="include/head.jsp" %>

        <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
        <script type="text/javascript">

            var auto_refresh = setInterval(
            function ()
            {
                $('#load_me').load('/FrontController').fadeIn("slow");
            }, 1);
        </script>
<body>
<% response.setIntHeader("Refresh", 1);%>
    <%@ include file="include/menu.jsp" %>
    <b><hr/><h2 align="center">Welcome</h2><hr/></b>

    <c:set var = "id" scope = "session" value = "${user.id}"/>
    <c:if test = "${id == 1}">
    </c:if>
    <div id="load_me">
     <ctg:info-time />
    </div>


    <%@ include file="include/footer.jsp" %>
</body>
</html>
