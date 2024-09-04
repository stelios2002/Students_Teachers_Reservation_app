<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ include file="topMenuStudent.jsp" %>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Make a Reservation</title>
<link href="styles1.css" rel="stylesheet" type="text/css" >
</head>
<body>

<div class="contentReservation">
    <form id="reservation-form" class="reservation-form" autocomplete="off" action="<%=request.getContextPath()%>/StudentServlet" method="post">
        <input type="hidden" name="action" value="commitReservation" />
        <h1 class="a11y-hidden">Reservation Form</h1>
        <div>
            <label class="label-id">
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
        <input type="submit" value="Make Reservation" />
    </form>
</div>

</body>
</html>
