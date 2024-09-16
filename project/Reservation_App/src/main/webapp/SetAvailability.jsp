
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
<form action="setAvailability" method="post">
        <input type="hidden" name="professorId" value="${professorId}">
        
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
            <input type="checkbox" name="timeSlots" value="10:00-10:30"> 10:00 AM - 10:30 AM<br>
            <input type="checkbox" name="timeSlots" value="10:30-11:00"> 10:30 AM - 11:00 AM<br>
            <input type="checkbox" name="timeSlots" value="11:00-11:30"> 11:00 AM - 11:30 AM<br>
            <input type="checkbox" name="timeSlots" value="11:30-12:00"> 11:30 AM - 12:00 PM<br>
            <input type="checkbox" name="timeSlots" value="12:00-12:30"> 12:00 PM - 12:30 PM<br>
            <input type="checkbox" name="timeSlots" value="12:30-13:00"> 12:30 PM - 1:00 PM<br>
            <input type="checkbox" name="timeSlots" value="13:00-13:30"> 1:00 PM - 1:30 PM<br>
            <input type="checkbox" name="timeSlots" value="13:30-14:00"> 1:30 PM - 2:00 PM<br>
            <input type="checkbox" name="timeSlots" value="14:00-14:30"> 2:00 PM - 2:30 PM<br>
            <input type="checkbox" name="timeSlots" value="14:30-15:00"> 2:30 PM - 3:00 PM<br>
            <input type="checkbox" name="timeSlots" value="15:00-15:30"> 3:00 PM - 3:30 PM<br>
            <input type="checkbox" name="timeSlots" value="15:30-16:00"> 3:30 PM - 4:00 PM<br>
            <input type="checkbox" name="timeSlots" value="16:00-16:30"> 4:00 PM - 4:30 PM<br>
            <input type="checkbox" name="timeSlots" value="16:30-17:00"> 4:30 PM - 5:00 PM<br>
            <input type="checkbox" name="timeSlots" value="17:00-17:30"> 5:00 PM - 5:30 PM<br>
            <input type="checkbox" name="timeSlots" value="17:30-18:00"> 5:30 PM - 6:00 PM<br>
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
