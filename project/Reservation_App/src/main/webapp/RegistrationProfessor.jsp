<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ include file="topMenu.jsp" %>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registration Page</title>
<link href="styles1.css" rel="stylesheet" type="text/css" >

</head>
<body>


<div class="contentRegister">
<form id="register-form" class="register-form" autocomplete="off" action="<%=request.getContextPath()%>/user" method="post">
<input type="hidden" name="action" value="registerProfessor" />
<h1 class="a11y-hidden">Registration Form</h1>
  <input type="checkbox" name="show-password" class="show-password a11y-hidden" id="show-password" tabindex="2" />
  <label class="label-show-password" for="show-password">
    <span>Show Password</span>
  </label>
  <div>
    <label class="label-id">
      <input type="text" class="text" name="id" placeholder="Professor Id" tabindex="4" required />
      <span class="required">Personal Id</span>
    </label>
  </div>
  <div>
    <label class="label-name">
      <input type="text" class="text" name="name" placeholder="Name" tabindex="4" required />
      <span class="required">Name</span>
    </label>
  </div>
  <div>
    <label class="label-surname">
      <input type="text" class="text" name="surname" placeholder="Name" tabindex="4" required />
      <span class="required">Surname</span>
    </label>
  </div>
  <div>
    <label class="label-department">
      <input type="text" class="text" name="dept" placeholder="Department" tabindex="5" required />
      <span class="required">Department</span>
    </label>
  </div>
  <div>
    <label class="label-school">
      <input type="text" class="text" name="school" placeholder="School" tabindex="5" required />
      <span class="required">School</span>
    </label>
  </div>
  <div>
    <label class="label-year">
      <input type="text" class="text" name="specialty" placeholder="Specialty" tabindex="5" required />
      <span class="required">Specialty</span>
    </label>
  </div>
  <div>
    <label class="label-username">
      <input type="text" class="text" name="username" placeholder="Username" tabindex="1" required />
      <span class="required">Username</span>
    </label>
  </div> 
  <div>
    <label class="label-password">
      <input type="text" class="text" name="password" placeholder="Password" tabindex="3" required />
      <span class="required">Password</span>
    </label>
  </div>
   	
  <input type="submit" value="Register" />
  
  
  
  </form>
 

  <br>
  </div>
</body>
</html>