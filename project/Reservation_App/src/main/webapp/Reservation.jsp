<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="topMenuStudent.jsp" %>
<html>
<head>
<meta charset="UTF-8">
<title>Make a Reservation</title>
<link href="styles1.css" rel="stylesheet" type="text/css" >
</head>
<body>
<div class="contentReservation">
    <form id="reservation-form" class="reservation-form" autocomplete="off" action="<%=request.getContextPath()%>/StudentServlet" method="post">
        <input type="hidden" name="action" value="commitReservation" />
        <h1 class="a11y-hidden">Reservation Form</h1>

        <div>
            <label class="label-professor">
                <input type="text" class="text" name="professor_id" placeholder="Professor Id" required />
                <span class="required">Professor Id</span>
            </label>
        </div>

      	<div>
            <label class="label-date">
                <input type="date" class="text" name="date" required />
                <span class="required">Date</span>
            </label>
        </div>
        <div>
            <label class="label-time">
                <input type="time" class="text" name="time" required />
                <span class="required">Time</span>
            </label>
        </div>
        <div>
            <label class="label-room">
                <input type="number" class="text" name="room" placeholder="Room Number" required />
                <span class="required">Room Number</span>
            </label>
        </div>        
        <div>
            <label class="label-comment">
                <input type="text" class="text" name="comment" placeholder="Comment" required />
                <span class="required">Comment</span>
            </label>
        </div>    
        <div>
           <label for="priority">Priority:</label>
        <select name="priority" id="priority">
        	<option value="0">Άλλο</option>
            <option value="1">Πτυχιακή</option>
            <option value="2">Αναβαθμολόγηση</option>
            <option value="3">Μεταπτυχιακό</option>
            <option value="4">Erasmus</option>
            <option value="5">Επίλυση Αποριών</option>
            
        </select><br>
        </div>

        <input type="submit" value="Make Reservation" />
  </form>
</div>

</body>
</html>
