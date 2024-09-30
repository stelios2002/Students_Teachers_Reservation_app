<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ page import="ReservationModule.users.models.Student" %>
<%@ include file="topMenuProfessor.jsp" %>
<html>
<head>
    <title>Student Information</title>
	<link href="styles1.css" rel="stylesheet" type="text/css" >
</head>
<body>
	<div class="container">
	    <div class="jumbotron">
	        <h1>Student Information</h1>
	        <p>Here is the detailed information about the student.</p>
	        <hr>
	        <% 
	            // Assuming the Student object is available as an attribute named "student"
	            Student student = (Student) request.getAttribute("student"); 
	            if (student != null) {
	        %>
	            <p><strong>ID:</strong> <%= student.getId() %></p>
	            <p><strong>Username:</strong> <%= student.getUsername() %></p>
	            <p><strong>Department:</strong> <%= student.getDepartment() %></p>
	            <p><strong>School:</strong> <%= student.getSchool() %></p>
	            <p><strong>Year:</strong> <%= student.getYear() %></p>
	        <% 
	            } else { 
	        %>
	            <p>No student information available.</p>
	        <% 
	            } 
	        %>
	        <form action="<%=request.getContextPath()%>/ProfessorServlet" method="post" style="display:inline;">
				<input type="submit" name="action" value="Show Students">
	        </form>
	    </div>
	</div>
</body>
</html>