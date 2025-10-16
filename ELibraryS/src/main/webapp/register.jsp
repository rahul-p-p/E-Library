<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>register-page</title>
<%@include file="components/style.jsp" %>
<style>
body{
	display:flex;
	flex-direction: column;
	background-color: #ecf5cb;
}
.card{
	align-self: center;
	width:30%;
	margin: 2rem 0 3rem 0;
}
</style>
</head>
<body>
	<div>
		<%@include file="components/navbar.jsp" %>
	</div>
    <div class="card p-5">
	    
	     <h3 class="text-center fw-bold">REGISTER</h3>
		<c:if test="${not empty success }">
			<p class="text-center text-success">${success }</p>
			<c:remove var="success" scope="session"/>
		</c:if>
		<c:if test="${not empty error }">
			<p class="text-center text-danger">${error }</p>
			<c:remove var="error" scope="session"/>
		</c:if>

		<form action="registercontroler" method="post">
	     	<div class="mb-2">
	     		<label for="fullname" class="form-label fw-bold">Full Name:</label>
	         	<input type="text" class="form-control" id="fullname" name="fullname" placeholder="Enter your full name" required>
	     	</div>
	     	<div class="mb-2">
	     		<label for="uname" class="form-label fw-bold">Username:</label>
	         	<input type="text" class="form-control" id="uname" name="uname" placeholder="Enter a uname" required>
	     	</div>
	     	<div class="mb-2">
	     		<label for="email" class="form-label fw-bold">Email:</label>
	        	<input type="email" class="form-control" id="email" name="email" placeholder="Enter your email" required>
	        </div>
	     	<div class="mb-2">
	     		<label for="pass" class="form-label fw-bold">Password:</label>
	        	<input type="password" class="form-control" id="pass" name="pass" placeholder="Enter password" required>
	     	</div>
	     	<div class="mb-2">
	     		<label for="cpass" class="form-label fw-bold">Confirm Password:</label>
	        	<input type="password" class="form-control" id="cpass" name="cpass" placeholder="Re-enter password" required><br>
	     	</div>
	        <button class="btn btn-primary fw-bold" style="margin-left: 40%">Register</button>      
	     </form>
	</div>
	<div>
		<%@include file="components/footer.jsp" %>
	</div>

</body>
</html>