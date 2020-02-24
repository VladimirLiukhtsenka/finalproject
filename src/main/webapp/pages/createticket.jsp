<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<%@ include file="include/head.jsp" %>
<body>
<div class="container">
<%@ include file="include/menu.jsp" %>
<h1 align="center"><fmt:message key="button.createTicket"/></h1>
  <form class="form-horizontal"action = "do?command=Create_ticket" method = "POST">
    <div class="form-group">
        <label class="col-md-4 control-label" for="type_seat"><fmt:message key="message.typeSeat"/></label>
        <div class="col-md-4">
          <select id="type_seat" name="type_seat" class="form-control" required="">
            <option value="Партер"><fmt:message key="dropMenu.parterre"/></option>
            <option value="Ложа"><fmt:message key="dropMenu.lodge"/></option>
            <option value="Бельэтаж"><fmt:message key="dropMenu.mezzanine"/></option>
            <option value="Балкон"><fmt:message key="dropMenu.balcony"/></option>
            <option value="Сектор A"><fmt:message key="dropMenu.sectorA"/></option>
            <option value="Сектор B"><fmt:message key="dropMenu.sectorB"/></option>
            <option value="Сектор C"><fmt:message key="dropMenu.sectorC"/></option>
            <option value="Сектор D"><fmt:message key="dropMenu.sectorD"/></option>
            <option value="Трибуна A"><fmt:message key="dropMenu.tribuneA"/></option>
            <option value="Трибуна B"><fmt:message key="dropMenu.tribuneB"/></option>
            <option value="Трибуна C"><fmt:message key="dropMenu.tribuneC"/></option>
            <option value="Трибуна D"><fmt:message key="dropMenu.tribuneD"/></option>
            <option value="Фанзона"><fmt:message key="dropMenu.fanZone"/></option>
            <option value="Танцпол"><fmt:message key="dropMenu.danceFloor"/></option>
          </select>
        </div>
    </div>
      <div class="form-group">
        <label class="col-md-4 control-label" for="price"><fmt:message key="message.price"/>*</label>
        <div class="col-md-4">
        <input id="price" name="price" type="text" placeholder="" class="form-control input-md" required="" pattern="(\d{1,4}(\.\d{1,2})?)">
        </div>
      </div>
      <div class="form-group">
        <label class="col-md-4 control-label" for="number_of_tickets"><fmt:message key="message.numberOfTickets"/>*</label>
        <div class="col-md-4">
        <input id="number_of_tickets" name="number_of_tickets" type="text" placeholder="" class="form-control input-md" required="" pattern="(\d{1,7})">
        </div>
     </div>
        <!-- Button -->
        <div class="form-group" action = "do?command=Create_ticket" method = "POST">
            <label class="col-md-4 control-label" for="create"></label>
            <div class="col-md-4">
                <button id="create" name="create" class="btn btn-success"><fmt:message key="button.createTicket"/></button>
            </div>
        </div>
    </form>
</div>
</body>
</html>