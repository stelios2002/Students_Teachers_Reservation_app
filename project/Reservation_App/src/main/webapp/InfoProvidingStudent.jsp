
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="topMenuStudent.jsp" %>
<%@ page import="java.util.List" %>
<%@ page import="ReservationModule.utils.models.Reservation" %>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Info Providing</title>
<link href="styles1.css" rel="stylesheet" type="text/css" >
</head>


<body>


<div style="margin-top: 150px;" class="content">

<h2>View Reservations</h2>

<p>
To view your reservations, click on the <strong>"RESERVATIONS"</strong> button from the top menu.<br></br>
Reservations are coloured differently based on their priority.<br></br>
1. ΠΤΥΧΙΑΚΗ --> <strong style="color: red;">RED</strong><br></br>
2. ΑΝΑΒΑΘΜΟΛΟΓΗΣΗ --> <strong style="color: orange;">ORANGE</strong><br></br>
3. ΜΕΤΑΠΤΥΧΙΑΚΟ --> <strong style="color: yellow;"">YELLOW</strong><br></br>
4. ERASMUS --> <strong style="color: blue;">BLUE</strong><br></br>
5. ΕΠΙΛΥΣΗ ΑΠΟΡΙΩΝ --> <strong style="color: green;">GREEN</strong><br></br>
6. ΑΛΛΟ --> <strong style="color: grey;">GREY</strong><br></br>
</p>


<h2>Make Reservation</h2>

<p>
To make a reservation, click on the <strong>"MAKE A RESERVATION"</strong> button from the top menu.<br></br>
Fill out the form and press the <strong>"MAKE RESERVATION"</strong> button to confirm.<br></br>
</p>


<h2>Show Professors</h2>

<p>
To view all professors, click on the <strong>"Show Professors"</strong> button from the top menu.<br></br>
For each one you can see their availability.<br></br>
</p>

</div>


</body>
