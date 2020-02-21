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
<%@ include file="/pages/include/menu.jsp" %>
<body>
    <table align="center" >
        <tr>
            <td>
                <form class="form-horizontal" action = "do?command=change_locale" method="POST">
                    <div class="form-group">
                      <div class="col-md-1">
                        <button id="en" name="en" class="btn btn-default">en</button>
                      </div>
                    </div>
                </form>
            </td>
            <td>
                <form class="form-horizontal" action = "do?command=change_locale" method="POST">
                    <div class="form-group">
                     <div class="col-md-1">
                        <button id="ru" name="ru" class="btn btn-default">ru</button>
                      </div>
                    </div>
                </form>
            </td>
        </tr>
    </table>
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
    <b><h2 align="center"><fmt:message key="message.title" /></h2></b>
    <b><h3 align="center"><fmt:message key="message.subtitleOne" />
    <br><fmt:message key="message.subtitleTwo"/></h3></b>
    <b><h4 align="center"><ctg:info-time /></h4></b>
    <b><h4 align="center"><span id="time">00:00:00</span></h4></b>
    <%@ include file="/pages/include/footer.jsp" %>
</body>
</html>
