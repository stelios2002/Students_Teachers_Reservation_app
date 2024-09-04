<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ include file="topMenuProfessor.jsp" %>
<%@ page import="java.util.List" %>
<%@ page import="ReservationModule.utils.models.Reservation" %>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Professor Page</title>
<link href="styles1.css" rel="stylesheet" type="text/css" >
</head>
<body>
<div class="content">
    <%
    String username = (String) session.getAttribute("username");

    if (username != null) {
    %>
    <div class="content">
        <h1> Accepted Reservations of <%= username %></h1>
        <table>
            <tr>
                <th>Student Id</th>
                <th>Date</th>
                <th>Time</th>
                <th>Room</th>
                <th>Actions</th>
            </tr>
            <%
                List<Reservation> reservations = (List<Reservation>) request.getAttribute("reservations");
                if (reservations != null && !reservations.isEmpty()) {
                    for (Reservation reservation : reservations) {
                        String reservationId = reservation.getId(); // Assume there is a method getId() to get the reservation ID
            %>
            <tr>
                <td><%= reservation.getStudent() %></td>
                <td><%= reservation.getDate() %></td>
                <td><%= reservation.getTime() %></td>
                <td><%= reservation.getRoom() %></td>
                <td>
                
                    <!-- Accept button -->
                    <form action="<%=request.getContextPath()%>/ProfessorServlet" method="post" style="display:inline;">
                    	<input type="hidden" name="action" value="acceptReservation">
                        <input type="hidden" name="reservationId" value="<%= reservation.getId() %>">
                        <input type="submit" value="Accept">
                    </form>
                    <!-- Update button -->
                    <form action="<%=request.getContextPath()%>/ProfessorServlet" method="post" style="display:inline;">
                    	<input type="hidden" name="action" value="updateReservation">
                        <input type="hidden" name="reservationId" value="<%= reservation.getId() %>">
                        <input type="submit" value="Update">
                    </form>
                    <!-- Delete button -->
                    <form action="<%=request.getContextPath()%>/ProfessorServlet" method="post" style="display:inline;">
                    	<input type="hidden" name="action" value="deleteReservation">
                        <input type="hidden" name="reservationId" value="<%= reservation.getId() %>">
                        <input type="submit" value="Delete">
                    </form>
                </td>
            </tr>
            <%
                    }
                } else {
            %>
            <tr>
                <td colspan="5">No reservations found.</td>
            </tr>
            <%
                }
            %>
        </table>
    </div>
    <%
        } else {
    %>
    <p>Please <a href="LoginPage.jsp">login</a>.</p>
    <%
        }
    %>
</div>
</body>
</html>