<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<%@ include file="include/head.jsp" %>
<body>
    <%@ include file="include/menu.jsp" %>
    <form class="form-horizontal"action = "do?command=update_user" method="POST">
    <h3 align="center">Update personal data</h3>
        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="name">Name</label>
            <div class="col-md-4">
                <input id="name" name="name" type="text" value="${user.name}" class="form-control input-md" required="" maxlength=45 pattern="^[\wа-яА-ЯёЁ]{3,45}$"><h5 style="color:#ff0000">${errorname}</h5>
            </div>
        </div>

        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="surname">Surname</label>
            <div class="col-md-4">
                <input id="surname" name="surname" type="text" value="${user.surName}" class="form-control input-md" required="" maxlength=45 pattern="^[\wа-яА-ЯёЁ]{3,45}$"><h5 style="color:#ff0000">${errorsurname}</h5>
            </div>
        </div>

        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="father name">Father name</label>
            <div class="col-md-4">
                <input id="father_name" name="father_name" type="text" value="${user.fatherName}" class="form-control input-md" required="" maxlength=45 pattern="^[\wа-яА-ЯёЁ]{3,45}$"><h5 style="color:#ff0000">${errorFatherName}</h5>
            </div>
        </div>

        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="mail">Mail</label>
            <div class="col-md-4">
                <input id="mail" name="mail" type="text" value="${user.mail}" class="form-control input-md" required="" maxlength=30 pattern="^[\w-\+]+(\.[\w-]+)*@[\w-]+(\.[\w]+)*(\.[a-zA-Z]{2,})$"><h5 style="color:#ff0000">${errormail}</h5>
            </div>
        </div>


        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="phone">Phone</label>
            <div class="col-md-4">
                <input id="phone" name="phone" type="text" value="${user.phone}" class="form-control input-md" required="" maxlength=13 pattern="^([+])*[\d]{12,13}$"><h5 style="color:#ff0000">${errorphone}</h5>
                <span class="help-block">for example +375251234567</span>
            </div>
        </div>

        <!-- Button -->
        <div class="form-group">
            <label class="col-md-4 control-label" for="submitButton"></label>
            <div class="col-md-4">
                <button id="submitButton" name="submitButton" class="btn btn-success">Update</button>
            </div>
        </div>
    </form>
    <%@ include file="include/footer.jsp" %>
</body>
</html>