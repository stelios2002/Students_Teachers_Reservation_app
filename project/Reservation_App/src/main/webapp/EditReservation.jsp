<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ page import="ReservationModule.utils.models.Reservation" %>
<% if(request.getParameter("type") == "student") { %>
<%@ include file="topMenuStudent.jsp" %>
<% } else { %>
<%@ include file="topMenuProfessor.jsp" %>
<% } %>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update the Reservation</title>
<link href="styles1.css" rel="stylesheet" type="text/css" >
</head>
<body>

<div class="contentReservation">
    <form id="reservation-form" class="reservation-form" autocomplete="off" action="
    <% 
    	if(request.getAttribute("type").equals("student")) { 
    %>
    	<%=request.getContextPath()%>/StudentServlet
    <%
    	} else { 
    %>
    	<%=request.getContextPath()%>/ProfessorServlet <% } %>" method="post">
        <input type="hidden" name="action" value="Confirm" />
        <h1 class="a11y-hidden">Reservation Form</h1>
		<% 
			Reservation reservation = (Reservation) request.getAttribute("reservation");
		%>
      	<div>
      		<input type="hidden" name="id" value="<%= reservation.getId() %>"/>
      		<input type="hidden" name="studid" value="<%= reservation.getStudent() %>"/>
      		<input type="hidden" name="profid" value="<%= reservation.getProfessor() %>"/>
            <label class="label-date">
                <input type="date" class="text" name="date" required />
                <span class="required" value="<%= reservation.getDate() %>">Date</span>
            </label>
        </div>
        <div>
            <label class="label-time">
                <input type="time" class="text" name="time" required />
                <span class="required" value="<%= reservation.getTime() %>">Time</span>
            </label>
        </div>
        <div>
            <label class="label-room">
                <input type="number" class="text" name="room" placeholder="Room Number" required />
                <span class="required" value="<%= reservation.getRoom() %>">Room Number</span>
            </label>
        </div>
        <input type="submit" value="Confirm" />
    </form>
</div>

</body>
</html>
