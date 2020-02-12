<%@ taglib prefix="ctg" uri="/WEB-INF/tld/custom.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<%@ include file="include/head.jsp" %>
<body>
    <%@ include file="include/menu.jsp" %>
    <script type="text/javascript">
        setInterval(function () {
         date = new Date(),
         h = date.getHours(),
         m = date.getMinutes(),
         s = date.getSeconds(),
         h = (h < 10) ? '0' + h : h,
         m = (m < 10) ? '0' + m : m,
         s = (s < 10) ? '0' + s : s,
         document.getElementById('time').innerHTML = h + ':' + m + ':' + s;
        }, 1000);
    </script>
    <b><hr/><h2 align="center">Welcome</h2><hr/></b>
    <b><hr/><h2 align="center"><ctg:info-time /></h2><hr/></b>
    <b><hr/><h2 align="center"><span id="time">00:00:00</span></h2><hr/></b>
    <%@ include file="include/footer.jsp" %>
</body>
</html>
