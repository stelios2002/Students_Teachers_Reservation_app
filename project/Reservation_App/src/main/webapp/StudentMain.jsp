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
        <h1>Reservations of <%= username %></h1>
        <table>
            <tr>
                <th>Professor ID</th>
                <th>Date</th>
                <th>Time</th>
                <th>Room</th>
                <th>Accepted</th>
                <th>Priority</th>
                <th>Comment</th>
            </tr>
            <%
                List<Reservation> reservations = (List<Reservation>) request.getAttribute("reservations");
                if (reservations != null && !reservations.isEmpty()) {
                    for (Reservation reservation : reservations) {
                        String priorityClass = "";
                        switch (reservation.getPriority()) {
                            case 1:
                                priorityClass = "priority-one";
                                break;
                            case 2:
                                priorityClass = "priority-two";
                                break;
                            case 3:
                                priorityClass = "priority-three";
                                break;
                            case 4:
                                priorityClass = "priority-four";
                                break;
                            case 5:
                                priorityClass = "priority-five";
                                break;
                            default:
                                priorityClass = "";
                        }
            %>
            <tr class="<%= priorityClass %>">
                <td><%= reservation.getProfessor() %></td>
                <td><%= reservation.getDate() %></td>
                <td><%= reservation.getTime() %></td>
                <td><%= reservation.getRoom() %></td>
                <td><%= reservation.isAccepted() ? "yes" : "no" %></td>
                <td><%= reservation.getPriority() %></td>
                <td><%= reservation.getComment() %></td>
            </tr>
            <%
                    }
                } else {
            %>
            <tr>
                <td colspan="7">No reservations found.</td>
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
