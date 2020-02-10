<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<%@ include file="include/head.jsp" %>
<body>
    <%@ include file="include/menu.jsp" %>
    <form class="form-horizontal"action = "do?command=sign_up" method="POST">

        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="name">Name*</label>
            <div class="col-md-4">
                <input id="name" name="name" type="text" value="${name}" class="form-control input-md" required=""><h5 style="color:#ff0000">${errorname}</h5>
            </div>
        </div>

        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="surname">Surname*</label>
            <div class="col-md-4">
                <input id="surname" name="surname" type="text" value="${surname}" class="form-control input-md" required=""><h5 style="color:#ff0000">${errorsurname}</h5>
            </div>
        </div>

        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="father name">Father name*</label>
            <div class="col-md-4">
                <input id="father_name" name="father_name" type="text" value="${father_name}" class="form-control input-md" required=""><h5 style="color:#ff0000">${errorFatherName}</h5>
            </div>
        </div>

        <!-- Select Basic -->
        <div class="form-group">
            <label class="col-md-4 control-label" for="gender">Gender*</label>
            <div class="col-md-4">
                <select id="gender" name="gender" class="form-control">
                    <option value="1" >male</option>
                    <option value="2">female</option>
                </select>
            </div>
        </div>

        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="mail">Mail*</label>
            <div class="col-md-4">
                <input id="mail" name="mail" type="text" value="${mail}" class="form-control input-md" required=""><h5 style="color:#ff0000">${errormail}</h5>
            </div>
        </div>


        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="phone">Phone*</label>
            <div class="col-md-4">
                <input id="phone" name="phone" type="text" value="${phone}" class="form-control input-md" required=""><h5 style="color:#ff0000">${errorphone}</h5>
                <span class="help-block">for example +375251234567</span>
            </div>
        </div>

        <!-- Password input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="password">Password*</label>
            <div class="col-md-4">
                <input id="password" name="password" type="password" value="${password}" class="form-control input-md" required=""><h5 style="color:#ff0000">${errorpassword}</h5>
                <span class="help-block">no less than 8 characters</span>
            </div>
        </div>

        <!-- Button -->
        <div class="form-group">
            <label class="col-md-4 control-label" for="submitButton"></label>
            <div class="col-md-4">
                <button id="submitButton" name="submitButton" class="btn btn-success">Signup</button>
            </div>
        </div>
    </form>
    <%@ include file="include/footer.jsp" %>
</body>
</html>




