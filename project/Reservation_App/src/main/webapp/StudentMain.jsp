
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
<style>
	 	  	  h1 {
	 	 	  text-align: center;
	 	 	  font-size:20px;
	 	    }
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
	        button {
	            padding: 5px 10px;
	            cursor: pointer;
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
        <h1>Reservations of <%= username %></h1>
        <table>
            <tr>
                <th>
                <form action="<%=request.getContextPath()%>/StudentServlet" method="post">
                <input type="hidden" name="alignment" value="1">
                <button type="submit" value="Reservations" name="action" class="sortButton">Professor Id
                </button>
                </form></th>
                <th colspan=2>
                <form action="<%=request.getContextPath()%>/StudentServlet" method="post">
                <input type="hidden" name="alignment" value="3">
                <button type="submit" value="Reservations" name="action" class="sortButton">Date / Time
                </button>
                </form></th>
                <th>Room</th>
                <th>Accepted</th>
                <th>
                <form action="<%=request.getContextPath()%>/StudentServlet" method="post">
                <input type="hidden" name="alignment" value="2">
                <button type="submit" value="Reservations" name="action" class="sortButton">Priority
                </button>
                </form>
                </th>
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
            <tr>
                <td class="<%= priorityClass %>"><%= reservation.getProfessor() %></td>
                <td class="<%= priorityClass %>"><%= reservation.getDate() %></td>
                <td class="<%= priorityClass %>"><%= reservation.getTime() %></td>
                <td class="<%= priorityClass %>"><%= reservation.getRoom() %></td>
                <td class="<%= priorityClass %>"><%= reservation.isAccepted() ? "yes" : "no" %></td>
                <td class="<%= priorityClass %>"><%= reservation.getPriority() %></td>
                <td class="<%= priorityClass %>"><%= reservation.getComment() %></td>
                 <td>
				<form class="buttonForm2" action="<%=request.getContextPath()%>/StudentServlet" method="post" style="display:inline;">
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
    <p>Please <a href="LoginPage.jsp">login</a>.</p>
    <%
        }
    %>
    </div>
</body>
</html>
