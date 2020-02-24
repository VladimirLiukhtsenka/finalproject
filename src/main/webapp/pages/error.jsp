<%@ page isErrorPage="true" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<html>
  <style>
   .fig {
    display: block;
    text-align: center;
    font-style: italic;
    margin-top: 0;
    margin-bottom: 5px;
    color: #666;
   }
  </style>
<%@ include file="include/head.jsp" %>
<body>
    <%@ include file="include/menu.jsp" %>
    <h1 align="center"><fmt:message key="message.error"/></h1>
    <figure class="fig">
       <img src="images/errorback.png" width="700" height="300" alt="error">
       <figcaption>
            <fmt:message key="message.errorSubOne"/><br>
            <fmt:message key="message.errorSubTwo"/> <a href=do?command=home><fmt:message key="navBar.home"/></a>
       </figcaption>
    </figure>
    <%@ include file="include/footer.jsp" %>
    <fmt:message key="message.requestFrom"/> ${pageContext.errorData.requestURI} <fmt:message key="message.isFailed"/><br/>
    <fmt:message key="message.servletName"/> ${pageContext.errorData.servletName}<br/>
    <fmt:message key="message.statusCode"/> ${pageContext.errorData.statusCode}<br/>
    <fmt:message key="message.exception"/> ${pageContext.exception}<br/>
</body>
</html>


