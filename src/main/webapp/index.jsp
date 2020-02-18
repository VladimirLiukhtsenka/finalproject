<%@ taglib prefix="ctg" uri="/WEB-INF/tld/custom.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<style>
body {
    background-size: cover;
    background-image: url("images/indexback.jpg");
}
</style>
<%@ include file="/pages/include/head.jsp" %>
<body>
    <%@ include file="/pages/include/menu.jsp" %>
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
    <b><h2 align="center">Welcome</h2></b>
    <b><h3 align="center">Just choose a ticket and pick it up absolutely free.<br> Please enjoy!</h3></b>
    <b><h4 align="center"><ctg:info-time /></h4></b>
    <b><h4 align="center"><span id="time">00:00:00</span></h4></b>
    <%@ include file="/pages/include/footer.jsp" %>
</body>
</html>
