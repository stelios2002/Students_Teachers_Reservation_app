<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
        button {
            padding: 5px 10px;
            cursor: pointer;
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
                <a href="RegistrationPage.jsp">
				   <input type="button" value="Register as a student" />
				</a>
            </td>
           
            </td>
        </tr>
        <tr>
            <td>
                <p>Professor. Select the button bellow to be registered as a professor</p>
                <a href="RegistrationPage.jsp">
				   <input type="button" value="Register as a professor" />
				</a>
            </td>
            
            
        </tr>
    </tbody>
</table>
</body>
</html>