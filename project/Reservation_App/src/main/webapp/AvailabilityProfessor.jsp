<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<%@ page import="java.util.Map" %>
<%@ page import="ReservationModule.utils.dao.AvailabilityDao" %>
<%@ include file="topMenuStudent.jsp" %>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Professor Availability</title>
<link href="styles1.css" rel="stylesheet" type="text/css">
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
	        td {
	        
	        	background-color:#333;
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
            a {
               color:white;
            }
</style>
</head>
<body>
    <%
        // Retrieve professorId from request attributes
        String professorId = request.getParameter("professorId");

        // Check if professorId is provided
        if (professorId == null || professorId.isEmpty()) {
    %>
    <p>No professor ID provided.</p>
    <%
        return; // Exit the JSP if professorId is not provided
        }
	
        // Retrieve the availability map from request attributes
        	AvailabilityDao dao = new AvailabilityDao();
            Map<Integer, boolean[]> availabilityMap = dao.getAvailabilityForProfessor(professorId);
     

        // Check if availabilityMap is available
        if (availabilityMap == null) {
    %>
    <p>No availability data found for Professor ID: <%= professorId %>.</p>
    <%
            return; // Exit the JSP if availabilityMap is not available
        }

        // Days of the week
        String[] days = { "Monday", "Tuesday", "Wednesday", "Thursday", "Friday" };
    %>
    
    <div class="content2">

    <h1>Availability for Professor ID: <%= professorId %></h1><br>

    <table>
        <tr>
            <th>Day</th>
            <th>Time Slot</th>
            <th>Availability</th>
        </tr>
        <%
            // Iterate over the availability map
            for (Map.Entry<Integer, boolean[]> entry : availabilityMap.entrySet()) {
                int dayIndex = entry.getKey();  // Day index (1-5)
                boolean[] timeslots = entry.getValue();
                
                // Display available time slots only
                for (int i = 0; i < timeslots.length; i++) { // Adjust to start at the 7th slot
                    if (timeslots[i]) {
                        String timeSlot = AvailabilityDao.getTimeSlotDisplay(i);
                        String available = timeslots[i] ? "Available" : "Not Available";
        %>
        <tr>
            <td><%= days[dayIndex - 1] %></td>
            <td><%= timeSlot %></td>
            <td><%= available %></td>
        </tr>
        <% 
                    }
                }
            }
        %>
    </table>
    </div>
</body>
</html>
