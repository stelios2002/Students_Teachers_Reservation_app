<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ include file="topMenuStudent.jsp" %>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Make a Reservation</title>
<link href="styles1.css" rel="stylesheet" type="text/css" >
</head>
<body>

<div class="contentReservation">
    <form id="reservation-form" class="reservation-form" autocomplete="off" action="<%=request.getContextPath()%>/StudentServlet" method="post">
        <input type="hidden" name="action" value="commitReservation" />
        <h1 class="a11y-hidden">Reservation Form</h1>


        <div>
            <label for="professor">Choose a professor:</label>
            <select name="id" id="id">
                <c:forEach var="professor" items="${professor}">
                    <option value="${professor.getId()}">${professor.getId()}</option>
                </c:forEach>
            </select>
        </div>

      	<div>
            <label for="date">Choose a date:</label>
            <select name="date" id="date">               
                <c:forEach var="professor" items="${professor}">
                    <option value="${professor.getDate()}">${professor.getDate(}</option>
                </c:forEach>
            </select>
      	</div>
      	
      	<div>
            <label for="time">Choose a time:</label>
            <select name="time" id="time">               
                <c:forEach var="professor" items="${professor}">
                    <option value="${professor.getTime()}">${professor.getTime()}</option>
                </c:forEach>
            </select>
      	</div>
      	
      	<div>
            <label for="room">Choose a room:</label>
            <select name="room" id="room">               
                <c:forEach var="professor" items="${professor}">
                    <option value="${professor.getRoom()}">${professor.getRoom()}</option>
                </c:forEach>
            </select>
      	</div>
      	

        <div>
            <label class="label-id">
                <input type="text" class="text" name="id" placeholder="Reservation Id" required />
                <span class="required">Reservation Id</span>
            </label>
        </div>

        <input type="submit" value="Make Reservation" />
    </form>
</div>

</body>
</html>