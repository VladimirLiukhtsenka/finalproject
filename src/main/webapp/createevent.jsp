<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<%@ include file="include/menu.htm" %>
<body>
<form class="form-horizontal"action = "do?command=createevent" method="POST" >
<fieldset>

<!-- Form Name -->
<legend>Create event</legend>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="name">Name</label>
  <div class="col-md-4">
  <input id="name" name="name" type="text" placeholder="" class="form-control input-md" required="">

  </div>
</div>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="address">Address</label>
  <div class="col-md-4">
  <input id="address" name="address" type="text" placeholder="" class="form-control input-md" required="">

  </div>
</div>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="description">Description</label>
  <div class="col-md-4">
  <input id="description" name="description" type="text" placeholder="" class="form-control input-md" required="">

  </div>
</div>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="date">Date</label>
  <div class="col-md-4">
  <input id="date" name="date" type="text" placeholder="" class="form-control input-md" required="">

  </div>
</div>

<!-- Multiple Radios -->
<div class="form-group">
  <label class="col-md-4 control-label" for="type of event">Type of event</label>
  <div class="col-md-4">
  <div class="radio">
    <label for="type of event-0">
      <input type="radio" name="type of event" id="type of event-0" value="Спорт" checked="checked">
      Sport
    </label>
	</div>
  <div class="radio">
    <label for="type of event-1">
      <input type="radio" name="type of event" id="type of event-1" value="Концерты">
      Concerts
    </label>
	</div>
  <div class="radio">
    <label for="type of event-2">
      <input type="radio" name="type of event" id="type of event-2" value="Фестивали">
      Festivals
    </label>
	</div>
  <div class="radio">
    <label for="type of event-3">
      <input type="radio" name="type of event" id="type of event-3" value="Театр">
      Theater
    </label>
	</div>
  <div class="radio">
    <label for="type of event-4">
      <input type="radio" name="type of event" id="type of event-4" value="Для детей">
      For children
    </label>
	</div>
  <div class="radio">
    <label for="type of event-5">
      <input type="radio" name="type of event" id="type of event-5" value="Кино">
      Movie
    </label>
	</div>
  </div>
</div>

<!-- Button -->
<div class="form-group">
  <label class="col-md-4 control-label" for="create"></label>
  <div class="col-md-4">
    <button id="create" name="create" class="btn btn-success">Create event</button>
  </div>
</div>

</fieldset>
</form>



<%@ include file="include/head.htm" %>
</body>
</html>