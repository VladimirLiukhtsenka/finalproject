<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<style>
body {
    background-size: cover;
    background-image: url("images/adminBcg.jpg");
}
</style>
<%@ include file="include/head.jsp" %>
<body>
<div class="container">
<%@ include file="include/menu.jsp" %>
<h1 align="center"><fmt:message key="button.createEvent"/></h1>
    <form class="form-horizontal"action = "do?command=create_event" method="POST" >

        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="name"><fmt:message key="field.name"/>*</label>
            <div class="col-md-4">
                <input id="name" name="name" type="text" placeholder="" class="form-control input-md" required="" maxlength=45 pattern="^[\p{Punct} \wа-яА-ЯёЁ]{3,45}$" >
            </div>
        </div>

        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="address"><fmt:message key="field.address"/>*</label>
            <div class="col-md-4">
                <input id="address" name="address" type="text" placeholder="" class="form-control input-md" required="" maxlength=45 pattern="^[\p{Punct} \wа-яА-ЯёЁ]{3,45}$">
            </div>
        </div>

        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="description"><fmt:message key="field.description"/>*</label>
            <div class="col-md-4">
                <input id="description" name="description" type="text" placeholder="" class="form-control input-md" required="" maxlength=60 pattern="^[\p{Punct} \wа-яА-ЯёЁ]{3,60}$">
            </div>
        </div>

        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="date"><fmt:message key="field.date"/>*</label>
            <div class="col-md-4">
                <input id="date" name="date" type="text" placeholder="" class="form-control input-md" required=""pattern="^\d{4}-\d{2}-\d{2} \d{2}:\d{2}:\d{2}$">
                <span class="help-block"><fmt:message key="message.dateFormat"/></span>
            </div>
        </div>

        <!-- Multiple Radios -->
        <div class="form-group">
            <label class="col-md-4 control-label" for="type of event"><fmt:message key="field.typeEvent"/></label>
            <div class="col-md-4">
                <div class="radio">
                    <label for="type_event-0">
                    <input type="radio" name="type_event" id="type_event-0" value="Спорт" checked="checked"><fmt:message key="navBar.sport"/></label>
                </div>
                <div class="radio">
                    <label for="type_event-1">
                    <input type="radio" name="type_event" id="type_event-1" value="Концерты"><fmt:message key="navBar.concerts"/></label>
                </div>
                <div class="radio">
                    <label for="type_event-2">
                    <input type="radio" name="type_event" id="type_event-2" value="Фестивали"><fmt:message key="navBar.festivals"/></label>
                </div>
                <div class="radio">
                    <label for="type_event-3">
                    <input type="radio" name="type_event" id="type_event-3" value="Театр"><fmt:message key="navBar.theater"/></label>
                </div>
                <div class="radio">
                    <label for="type_event-4">
                    <input type="radio" name="type_event" id="type_event-4" value="Для детей"><fmt:message key="navBar.forChildren"/></label>
                </div>
                <div class="radio">
                    <label for="type_event-5">
                    <input type="radio" name="type_event" id="type_event-5" value="Кино"><fmt:message key="navBar.movie"/></label>
                </div>
            </div>
        </div>

        <!-- Button -->
        <div class="form-group" >
            <label class="col-md-4 control-label" for="create"></label>
            <div class="col-md-4">
                <button id="create" name="create" class="btn btn-success"><fmt:message key="button.createEvent"/></button>
            </div>
        </div>
    </form>
</div>
</body>
</html>