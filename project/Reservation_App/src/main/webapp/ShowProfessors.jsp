<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ page import="java.util.ArrayList" %>
<%@ page import="ReservationModule.users.models.Professor" %>
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
<div class="content">
        <%
        
        String username = (String) session.getAttribute("username");

        if (username != null) {
    %>
    <div class="content">
        <h1>Professors of <%= username %></h1>
        <table>
            <tr>
                <th>Professor ID</th>
                <th>Username</th>
                <th>Department</th>
                <th>School</th>
                <th>Specialty</th>
            </tr>
            <%
                ArrayList<Professor> professors = (ArrayList<Professor>) request.getAttribute("professors");
                if (professors != null && !professors.isEmpty()) {
                    for (Professor professor : professors) {
            %>
            <tr>
                <td><a href="AvailabilityProfessor.jsp?professorId=<%= professor.getId() %>"><%= professor.getId() %></a></td>
                <td><%= professor.getUsername() %></td>
                <td><%= professor.getDepartment() %></td>
                <td><%= professor.getSchool() %></td>
                <td><%= professor.getSpecialty() %></td>
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
         	}
        
    %>
    </div>
</body>
</html>