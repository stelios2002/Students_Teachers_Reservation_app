<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="topMenuProfessor.jsp" %>
<%@ page import="java.util.List" %>
<%@ page import="ReservationModule.utils.models.Reservation" %>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Info Providing</title>
		<link href="styles1.css" rel="stylesheet" type="text/css" >
	</head>
	<body>
		<div>
			<h2>Confirm Reservations</h2>
			<p>
				To confirm a reservation of a student, click on the <strong>"CONFRIM RESERVATIONS"</strong> button from the top menu.<br></br>
				Reservations are coloured differently based on their priority.<br></br>
				1. ΠΤΥΧΙΑΚΗ --> <strong>RED</strong><br></br>
				2. ΑΝΑΒΑΘΜΟΛΟΓΗΣΗ --> <strong>ORANGE</strong><br></br>
				3. ΜΕΤΑΠΤΥΧΙΑΚΟ --> <strong>YELLOW</strong><br></br>
				4. ERASMUS --> <strong>BLUE</strong><br></br>
				5. ΕΠΙΛΥΣΗ ΑΠΟΡΙΩΝ --> <strong>GREEN</strong><br></br>
				6. ΑΛΛΟ --> <strong>GREY</strong><br></br>
				</p>
				<h2>View Reservations</h2>
				<p>
				To view accepted reservations, click on the <strong>"RESERVATIONS"</strong> button from the top menu.<br></br>
				From there, you can update/edit each reservation or delete it if necessary. 
				</p>
				<h2>Set Availability</h2>
				<p>
				To set your availability, click on the <strong>"SET AVAILABILITY"</strong> button from the top menu.<br></br>
				Choose the day of the week and the time of your liking, to inform the students when you are available.
			</p>
		</div>
	</body>
</html>