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
    <h1 align="center">Error</h1>
    <figure class="fig">
       <img src="images/errorback.png" width="700" height="300" alt="error">
       <figcaption>
            The rabbits have been nibbling the cables again...<br>
            May be this will help <a href=do?command=home>Home</a>
       </figcaption>
    </figure>
    <%@ include file="include/footer.jsp" %>
    Request from ${pageContext.errorData.requestURI} is failed<br/>
    Servlet name: ${pageContext.errorData.servletName}<br/>
    Status code: ${pageContext.errorData.statusCode}<br/>
    Exception: ${pageContext.exception}<br/>
</body>
</html>


