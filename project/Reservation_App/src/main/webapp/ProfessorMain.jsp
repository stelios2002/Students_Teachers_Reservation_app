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
<style>
	 	    table {
	            width: 100%;
	            border-collapse: collapse;
	        }
	        th {
	        
	        	background-color:black;
	            color:white;
	            
	        }
	        th, td {
	            border: 1px solid white;
	            padding: 10px;
	            text-align: left;
	            
	        }
	         .sortButton {
            	background: none;
				color: inherit;
				border: none;
				padding: 0;
				font: inherit;
				cursor: pointer;
				outline: inherit;
            }
             form {
            	background: none;
				color: inherit;
				border: none;
				padding: 0;
				font: inherit;
				cursor: pointer;
				outline: inherit;
            }
</style>

</head>
<body>
<div class="content">
    <%
    String username = (String) session.getAttribute("username");

    if (username != null) {
    %>
    <div class="content">
        <h1> Unaccepted Reservations of <%= username %></h1>
        <table>
            <tr>
                <th><form action="<%=request.getContextPath()%>/ProfessorServlet" method="post">
                <input type="hidden" name="alignment" value="1">
                <button type="submit" value="Confirm Reservations" name="action" class="sortButton">Student Id
                </button></form></th>
                <th colspan=2><form action="<%=request.getContextPath()%>/ProfessorServlet" method="post">
                <input type="hidden" name="alignment" value="3">
                <button type="submit" value="Confirm Reservations" name="action" class="sortButton">Date / Time
                </button></form></th>
                <th>Room</th>
                <th><form action="<%=request.getContextPath()%>/ProfessorServlet" method="post">
				<input type="hidden" name="alignment" value="2">
                <button type="submit" value="Confirm Reservations" name="action" class="sortButton">Priority
                </button></form></th>
                <th>Comment</th>
                <th>Actions</th>
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
                <td><%= reservation.getStudent() %></td>
                <td><%= reservation.getDate() %></td>
                <td><%= reservation.getTime() %></td>
                <td><%= reservation.getRoom() %></td>
                <td><%= reservation.getPriority() %></td>
                <td><%= reservation.getComment() %></td>
                <td>
                
                    <!-- Accept button -->
                    <form class="buttonForm" action="<%=request.getContextPath()%>/ProfessorServlet" method="post" style="display:inline;">
                    	<input type="hidden" name="action" value="acceptReservation">
                        <input type="hidden" name="reservationId" value="<%= reservation.getId() %>">
                        <input class="acceptB" type="submit" value="Accept">
                    </form>
                    <!-- Delete button -->
                    <form class="buttonForm2" action="<%=request.getContextPath()%>/ProfessorServlet" method="post" style="display:inline;">
                    	<input type="hidden" name="action" value="deleteReservation">
                        <input type="hidden" name="reservationId" value="<%= reservation.getId() %>">
                        <input class="deleteB" type="submit" value="Delete">
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