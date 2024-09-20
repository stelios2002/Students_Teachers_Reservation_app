<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ include file="topMenu.jsp" %>
<html>
	<head>
	<meta charset="ISO-8859-1">
	<title>Choose Role</title>
	<link href="styles1	.css" rel="stylesheet" type="text/css" >
	<style>
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
			border: 1px solid #000;
			padding: 10px;
			text-align: left;
		}
		input {
			padding: 5px 10px;
			cursor: pointer;
			font-size: 0.75rem;
			font-size: 0.75rem;
			font-weight: bold;
			margin-top: 0.625rem;
			order: 4;
			outline: 1px dashed transparent;
			outline-offset: 2px;
			padding-left: 0;
			text-transform: uppercase;
		}
		input:focus {
			outline: 1px dashed hsl(var(--fgColorH), calc(var(--fgColorS) * 2), calc(var(--fgColorL) * 1.15));
			outline-offset: 2px;
		}
		input:focus {
			background: hsl(var(--fgColorH), var(--fgColorS), calc(var(--fgColorL) * 0.85));
		}
		input:hover {
			background:;
		}
		input:active {
			background: hsl(var(--fgColorH), calc(var(--fgColorS) * 2), calc(var(--fgColorL) * 1.15));
			transition: all 0.125s;
		}
		.already {
			color: hsl(var(--bgColorH), var(--bgColorS), var(--bgColorL));
			font-size: 0.80rem;
			font-weight: bold;
			margin-top: 0.625rem;
			order: 4;
			outline: 1px dashed transparent;
			outline-offset: 2px;
			padding-left: 0;
			text-transform: uppercase;
		}
		.already:focus {
			outline: 1px dashed hsl(var(--fgColorH), calc(var(--fgColorS) * 2), calc(var(--fgColorL) * 1.15));
			outline-offset: 2px;
		}
		.already:focus {
			background: hsl(var(--fgColorH), var(--fgColorS), calc(var(--fgColorL) * 0.85));
		}
		.already:hover {
			background: hsl(var(--fgColorH), var(--fgColorS), calc(var(--fgColorL) * 0.85));
		}
		.already:active {
			background: hsl(var(--fgColorH), calc(var(--fgColorS) * 2), calc(var(--fgColorL) * 1.15));
			transition: all 0.125s;
		}
	</style>
	</head>
	<body>
		<table>
			<thead>
				<tr>
					<th>What type of user you want to register as</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>
						<p>Student. Select the button bellow to be registered as a student</p>
						<a href="RegistrationStudent.jsp">
							<input type="button" value="Register as a student" />
						</a>
					</td>
				</tr>
				<tr>
					<td>
						<p>Professor. Select the button bellow to be registered as a professor</p>
						<a href="RegistrationProfessor.jsp">
							<input type="button" value="Register as a professor" />
						</a>
					</td>
				<tr>
					<td>
						<a class="already" href="LoginPage.jsp">Already Registered?Click Here to Login</a>
					</td>
				</tr>
			</tbody>
		</table>
	</body>
</html>