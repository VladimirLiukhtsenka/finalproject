<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<%@ include file="include/head.jsp" %>
<body>
<div class="container">
<%@ include file="include/menu.jsp" %>
<h1 align="center">Create ticket</h1>
  <form class="form-horizontal"action = "do?command=Create_ticket" method = "POST">
    <div class="form-group">
        <label class="col-md-4 control-label" for="type_seat">Type seat</label>
        <div class="col-md-4">
          <select id="type_seat" name="type_seat" class="form-control" required="">
            <option value="Партер">Parterre</option>
            <option value="Ложа">Lodge</option>
            <option value="Бельэтаж">Mezzanine</option>
            <option value="Балкон">Balcony</option>
            <option value="Сектор A">Sector A</option>
            <option value="Сектор B">Sector B</option>
            <option value="Сектор C">Sector C</option>
            <option value="Сектор D">Sector D</option>
            <option value="Трибуна A">Tribune A</option>
            <option value="Трибуна B">Tribune B</option>
            <option value="Трибуна C">Tribune C</option>
            <option value="Трибуна D">Tribune D</option>
            <option value="Фанзона">Fan zone</option>
            <option value="Танцпол">Dance floor</option>
          </select>
        </div>
    </div>
      <div class="form-group">
        <label class="col-md-4 control-label" for="price">Price BYN</label>
        <div class="col-md-4">
        <input id="price" name="price" type="text" placeholder="" class="form-control input-md" required="" pattern="(\d{1,4}(\.\d{1,2})?)">
        </div>
      </div>
      <div class="form-group">
        <label class="col-md-4 control-label" for="number_of_tickets">Number of tickets</label>
        <div class="col-md-4">
        <input id="number_of_tickets" name="number_of_tickets" type="text" placeholder="" class="form-control input-md" required="" pattern="(\d{1,7})">
        </div>
     </div>
        <!-- Button -->
        <div class="form-group" action = "do?command=Create_ticket" method = "POST">
            <label class="col-md-4 control-label" for="create"></label>
            <div class="col-md-4">
                <button id="create" name="create" class="btn btn-success">Create ticket</button>
            </div>
        </div>
    </form>
</div>
</body>
</html>