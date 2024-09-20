<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ page import="java.util.ArrayList" %>
<%@ page import="ReservationModule.users.models.Student" %>
<%@ include file="topMenuProfessor.jsp" %>
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
		</style>
	</head>
	<body>
		<div class="content">
			<%
				String username = (String) session.getAttribute("username");
				if (username != null) {
			%>
			<div class="content">
				<h1>Students</h1>
				<table>
					<tr>
						<th>Student ID</th>
						<th>Username</th>
						<th>Department</th>
						<th>School</th>
						<th>Specialty</th>
						<th>Show Student</th>
					</tr>
					<%
						@SuppressWarnings("unchecked")
						ArrayList<Student> students = (ArrayList<Student>) request.getAttribute("students");
						if (students != null && !students.isEmpty()) {
						for (Student student : students) {
						String id = student.getId();
					%>
					<tr>
						<td><%= id %></td>
						<td><%= student.getUsername() %></td>
						<td><%= student.getDepartment() %></td>
						<td><%= student.getSchool() %></td>
						<td><%= student.getYear() %></td>
						<td>
							<form action="<%=request.getContextPath()%>/ProfessorServlet" method="post" style="display:inline;">
								<input type="hidden" name="action" value="selectStudent">
								<input type="hidden" name="studid" value="<%= id %>">
								<input type="submit" value="Show">
							</form>
						</td>
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
			}
			%>
		</div>
	</body>
</html>