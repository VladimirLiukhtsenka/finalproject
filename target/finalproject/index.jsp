<%@ taglib prefix="ctg" uri="/WEB-INF/tld/custom.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<%@ include file="include/head.jsp" %>
<body>
    <%@ include file="include/menu.jsp" %>
    <b><hr/><h2 align="center">Welcome</h2><hr/></b>

<c:set var = "id" scope = "session" value = "${user.id}"/>
<c:if test = "${id == 1}">


</c:if>
    <ctg:info-time/>
    <%@ include file="include/footer.jsp" %>
</body>
</html>
