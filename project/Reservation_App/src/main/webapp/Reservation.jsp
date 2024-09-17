<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="topMenuStudent.jsp" %>
<html>
<head>
<meta charset="UTF-8">
<title>Make a Reservation</title>
<link href="styles1.css" rel="stylesheet" type="text/css" >
<script>
// Script to set the min date to today's date
window.onload = function() {
    var today = new Date().toISOString().split('T')[0];  // Get today's date in 'YYYY-MM-DD' format
    document.getElementById("reservation-date").setAttribute("min", today);  // Set min attribute
};
</script>
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
                <input type="date" id="reservation-date" class="text" name="date" required />
                <span class="required">Date</span>
            </label>
        </div>


        <div>
         <span class="required">Time</span>    
            <label class="label-time">       
                <select class="text" name="time" required>
                    <option value="10:00">10:00 AM</option>
                    <option value="10:30">10:30 AM</option>
                    <option value="11:00">11:00 AM</option>
                    <option value="11:30">11:30 AM</option>
                    <option value="12:00">12:00 PM</option>
                    <option value="12:30">12:30 PM</option>
                    <option value="13:00">01:00 PM</option>
                    <option value="13:30">01:30 PM</option>
                    <option value="14:00">02:00 PM</option>
                    <option value="14:30">02:30 PM</option>
                    <option value="15:00">03:00 PM</option>
                    <option value="15:30">03:30 PM</option>
                    <option value="16:00">04:00 PM</option>
                    <option value="16:30">04:30 PM</option>
                    <option value="17:00">05:00 PM</option>
                    <option value="17:30">05:30 PM</option>
                </select>
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
