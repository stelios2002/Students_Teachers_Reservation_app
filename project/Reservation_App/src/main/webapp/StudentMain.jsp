<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ page import="java.util.List" %>
<%@ page import="ReservationModule.utils.models.Reservation" %>
<%@ include file="topMenuStudent.jsp" %>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Student Page</title>
<link href="styles1.css" rel="stylesheet" type="text/css" >
</head>
<body>
<div class="content">
        <%
        
        String username = (String) session.getAttribute("username");

        if (username != null) {
    %>
    <div class="content">
        <h1>Call History for <%= username %></h1>
        <table>
            <tr>
                <th>Professor ID</th>
                <th>Date</th>
                <th>Time</th>
                <th>Room</th>
                <th>Accepted</th>
            </tr>
            <%
                List<Reservation> reservations = (List<Reservation>) request.getAttribute("reservations");
                if (reservations != null && !reservations.isEmpty()) {
                    for (Reservation reservation : reservations) {
            %>
            <tr>
                <td><%= reservation.getProfessor() %></td>
                <td><%= reservation.getDate() %></td>
                <td><%= reservation.getTime() %></td>
                <td><%= reservation.getRoom() %></td>
                <td>
                <% 
                if (reservation.isAccepted()) {
                %>
                yes
                <%
                	} else {
                %>
                no
                <%
                } 
                %>
                </td>
            </tr>
            <%
                    }
                } else {
            %>
            <tr>
                <td colspan="5">No Reservations found.</td>
            </tr>
            <%
                }
            %>
        </table>
    </div>
    <%
        } else {
    %>
    <p>Please <a href="login.jsp">login</a>.</p>
    <%
        }
    %>
    </div>
</body>
</html>
