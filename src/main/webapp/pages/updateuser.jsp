<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<style>
body {
    background-size: cover;
    background-image: url("images/updateUserBack.jpg");
}
.button {
  font: bold 11px Arial;
  text-decoration: none;
  background-color: #EEEEEE;
    padding:5px;
    border:2px solid #ccc;
    -webkit-border-radius: 5px;
    border-radius: 5px;
}
</style>
<%@ include file="include/head.jsp" %>
<body>
    <%@ include file="include/menu.jsp" %>
    <div class="form-group" align="center">
        <c:choose>
          <c:when test="${user.photo == null}">
            <img src="images/photoMissing.jpg" width="240" height="240"/>
          </c:when>
          <c:when test="${user.photo != null}">
            <img src="data:image/jpg;base64,${user.photo}" width="240" height="240"/>
          </c:when>
        </c:choose>
        <form method="post" action="uploadController" enctype="multipart/form-data">
            <input type="file" id = "files" name="photo" class = "hidden"/>
            <input type="submit" class="button" value=<fmt:message key="message.save"/>>
            <label for = "files"  class="button" > <fmt:message key="message.choseFile"/> </ label>
        </form>
    </div>
    <c:set var = "data" scope = "session" value = "${errorPhoto}"/>
    <c:if test = "${data != null}">
        <h5 align="center" style="color:#ff0000"><fmt:message key="message.wrongFormat"/></h5>
    </c:if>
  <form class="form-horizontal" action = "do?command=update_user" method="POST">
    <h3 align="center"><fmt:message key="message.updatePersonData"/></h3>
        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="name"><fmt:message key="message.namePerson"/></label>
            <div class="col-md-4">
                <input id="name" name="name" type="text" value="${user.name}" class="form-control input-md" required="" maxlength=45 pattern="^[a-zA-Z_0-9а-яА-ЯёЁ]{3,45}$"><h5 style="color:#ff0000">${errorname}</h5>
            </div>
        </div>

        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="surname"><fmt:message key="message.surname"/></label>
            <div class="col-md-4">
                <input id="surname" name="surname" type="text" value="${user.surName}" class="form-control input-md" required="" maxlength=45 pattern="^[a-zA-Z_0-9а-яА-ЯёЁ]{3,45}$"><h5 style="color:#ff0000">${errorsurname}</h5>
            </div>
        </div>

        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="father name"><fmt:message key="message.fatherName"/></label>
            <div class="col-md-4">
                <input id="father_name" name="father_name" type="text" value="${user.fatherName}" class="form-control input-md" required="" maxlength=45 pattern="^[a-zA-Z_0-9а-яА-ЯёЁ]{3,45}$"><h5 style="color:#ff0000">${errorFatherName}</h5>
            </div>
        </div>

        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="mail"><fmt:message key="message.mail"/></label>
            <div class="col-md-4">
                <input id="mail" name="mail" type="text" value="${user.mail}" class="form-control input-md" required="" readonly><h5 style="color:#ff0000">${errormail}</h5>
            </div>
        </div>


        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="phone"><fmt:message key="message.phone"/></label>
            <div class="col-md-4">
                <input id="phone" name="phone" type="text" value="${user.phone}" class="form-control input-md" required="" maxlength=13 pattern="^([+])*[\d]{12,13}$"><h5 style="color:#ff0000">${errorphone}</h5>
                <span class="help-block"><fmt:message key="message.phone.help"/></span>
            </div>
        </div>

         <form class="form-horizontal" action = "do?command=update_user" method="POST">
            <label class="col-md-4 control-label" for="submitButton"></label>
               <div class="col-md-4">
                   <button id="submitButton" name="submitButton" class="btn btn-success"><fmt:message key="message.save"/></button>
               </div>
        </form>
    </form>
    <%@ include file="include/footer.jsp" %>
</body>
</html>