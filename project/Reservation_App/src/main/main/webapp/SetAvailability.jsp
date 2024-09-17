
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ include file="topMenuProfessor.jsp" %>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Make a Reservation</title>
<link href="styles1.css" rel="stylesheet" type="text/css" >
</head>
<body>
<div class = "content2">
	<form action="<%=request.getContextPath()%>/ProfessorServlet" method="post">
		<input type="hidden" name="action" value="SetAvailability">
        
        <!-- Select day of the week -->
        <label for="dayOfWeek">Day of the Week:</label>
        <select name="dayOfWeek" id="dayOfWeek">
            <option value="Monday">Monday</option>
            <option value="Tuesday">Tuesday</option>
            <option value="Wednesday">Wednesday</option>
            <option value="Thursday">Thursday</option>
            <option value="Friday">Friday</option>
        </select><br>

        <!-- Checkboxes for Time Slots -->
        
        <div>
        	<label for="timeSlots">Select Time Slots:</label><br>
            <input type="checkbox" name="timeSlots" value="0"> 10:00 AM - 10:30 AM<br>
            <input type="checkbox" name="timeSlots" value="1"> 10:30 AM - 11:00 AM<br>
            <input type="checkbox" name="timeSlots" value="2"> 11:00 AM - 11:30 AM<br>
            <input type="checkbox" name="timeSlots" value="3"> 11:30 AM - 12:00 PM<br>
            <input type="checkbox" name="timeSlots" value="4"> 12:00 PM - 12:30 PM<br>
            <input type="checkbox" name="timeSlots" value="5"> 12:30 PM - 1:00 PM<br>
            <input type="checkbox" name="timeSlots" value="6"> 1:00 PM - 1:30 PM<br>
            <input type="checkbox" name="timeSlots" value="7"> 1:30 PM - 2:00 PM<br>
            <input type="checkbox" name="timeSlots" value="8"> 2:00 PM - 2:30 PM<br>
            <input type="checkbox" name="timeSlots" value="9"> 2:30 PM - 3:00 PM<br>
            <input type="checkbox" name="timeSlots" value="10"> 3:00 PM - 3:30 PM<br>
            <input type="checkbox" name="timeSlots" value="11"> 3:30 PM - 4:00 PM<br>
            <input type="checkbox" name="timeSlots" value="12"> 4:00 PM - 4:30 PM<br>
            <input type="checkbox" name="timeSlots" value="13"> 4:30 PM - 5:00 PM<br>
            <input type="checkbox" name="timeSlots" value="14"> 5:00 PM - 5:30 PM<br>
            <input type="checkbox" name="timeSlots" value="15"> 5:30 PM - 6:00 PM<br>
            <!-- Add more time slots as needed -->
        </div>

        <!-- Submit button -->
        <div>
        <br>
        <button type="submit">Set Availability</button>
        </div>
    </form>

</div>

</body>
</html>